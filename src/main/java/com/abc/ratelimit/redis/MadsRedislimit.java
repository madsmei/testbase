package com.abc.ratelimit.redis;

import java.lang.annotation.*;

/**
 * @Description: redis限流的注解
 * @Date 2020/2/13
 * @Version V1.0
 * @Author Mads
 **/
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MadsRedislimit {
    //最大的限流数量
    double limit() default 1.0;

    //处理降级的方法名
    String fallback() default "fallbackMethod";
}
