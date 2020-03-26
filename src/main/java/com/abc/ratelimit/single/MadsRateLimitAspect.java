package com.abc.ratelimit.single;

import com.google.common.util.concurrent.RateLimiter;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;

/**
 * @Description: 单机版  令牌桶的切面
 * @Date 2020/2/13
 * @Version V1.0
 * @Author Mads
 **/
@Aspect
@Component
public class MadsRateLimitAspect {

    //令牌桶
    private RateLimiter rateLimiter ;

    /*****
     * 环绕通知。
     * 这样 所有使用了 MadsRatelimit注解的方法 都会被拦截
     * @param joinPoint
     * @param ratelimit
     * @return
     */
    @Around("@annotation(ratelimit)")
    public Object around(ProceedingJoinPoint joinPoint,MadsRatelimit ratelimit) {


        //写代码时 一定要 分布式的思想，
        if(null == rateLimiter) {
            synchronized (this) {
                if(null == rateLimiter) {
                    rateLimiter = RateLimiter.create(ratelimit.limit());
                }
            }
        }
        //试着去拿令牌，
        if(rateLimiter.tryAcquire(ratelimit.timeout(), TimeUnit.MILLISECONDS)){
            //调用业务代码
            try {
                return joinPoint.proceed();
            } catch (Throwable throwable) {
                throwable.printStackTrace();
            }
        }else {
            //拿锁失败，返回一个友好的错误信息，服务降级

            //拿到 服务降级的方法名。
            String method = ratelimit.fallback();

            try {
                //反射 调用 降级方法
                Method method1 = joinPoint.getTarget().getClass().getMethod(method, null);

                return  method1.invoke(joinPoint.getTarget(), null);
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }


        }


        return null;
    }
}
