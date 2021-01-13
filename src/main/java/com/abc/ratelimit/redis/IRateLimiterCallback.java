package com.abc.ratelimit.redis;

/**
 * 限流的回调
 */
public interface IRateLimiterCallback {
    boolean call();
}
