package com.abc.ratelimit.redis;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @Description: 令牌桶的切面
 * @Date 2020/2/13
 * @Version V1.0
 * @Author Mads
 **/
@Aspect
@Component
public class MadsRedisLimitAspect {


    /*****
     * 环绕通知。这样 所有使用了 MadsRatelimit注解的方法 都会被拦截
     * @param joinPoint
     * @param ratelimit
     * @return
     */
    @Around("@annotation(ratelimit)")
    public Object around(ProceedingJoinPoint joinPoint, MadsRedislimit ratelimit) {
        //试着去拿令牌，
        if(RedisLimitUtil.access()){
            System.out.println("---------redis 限流 拿令牌成功----");
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
