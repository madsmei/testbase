package com.abc.ratelimit.redis;

import org.springframework.stereotype.Component;

/**
 */
@Component
public class DefaultRateLimiterCallback implements IRateLimiterCallback {
    @Override
    public boolean call() {
        System.out.println("DefaultRateLimiterCallback.call");
        return true;
    }
}
