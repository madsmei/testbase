package com.abc.ratelimit.redis;

import org.redisson.api.RateIntervalUnit;
import java.lang.annotation.*;

/**
 * redis限流的注解
 **/
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MadsRedisLimit {

    String name() default "";

    int rate();//速率

    int rateInterval();//区间，间隔

    boolean useUserId() default true;

    RateIntervalUnit rateIntervalUnit() default RateIntervalUnit.SECONDS;

    Class<? extends IRateLimiterCallback> callback() default DefaultRateLimiterCallback.class;

    Class<? extends IRateLimiterNameGenerator> nameGenerator() default DefaultRateLimiterNameGenerator.class;
}
