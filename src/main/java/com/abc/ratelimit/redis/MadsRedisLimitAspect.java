package com.abc.ratelimit.redis;

import org.apache.tomcat.util.http.ResponseUtil;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.redisson.api.RRateLimiter;
import org.redisson.api.RateType;
import org.redisson.api.RedissonClient;
import org.redisson.client.RedisClient;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * 令牌桶的切面
 **/
@Aspect
@Component
public class MadsRedisLimitAspect implements ApplicationContextAware {

    @Autowired
    private RedissonClient redissonClient;
    private ApplicationContext applicationContext;


    /*****
     * 环绕通知。这样 所有使用了 MadsRatelimit注解的方法 都会被拦截
     */
    @Around("@annotation(rateLimiterAnno)")
    public Object around(ProceedingJoinPoint joinPoint, MadsRedisLimit rateLimiterAnno) {

        String key = String.format("rate:limiter:%s", rateLimiterAnno.name());

        RRateLimiter rateLimiter = redissonClient.getRateLimiter(key);
        rateLimiter.trySetRate(RateType.OVERALL,
                rateLimiterAnno.rate(),
                rateLimiterAnno.rateInterval(),
                rateLimiterAnno.rateIntervalUnit());
        if (!rateLimiter.tryAcquire()) {
            IRateLimiterCallback rateLimitCallback = applicationContext.getBean(rateLimiterAnno.callback());
            if (rateLimitCallback.call()) {
                System.out.println("too many request");
                return false;
            }
        }
        //调用业务代码
        try {
            return joinPoint.proceed();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }

        return null;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
