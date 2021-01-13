package com.abc.ratelimit.redis;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 */
public interface IRateLimiterNameGenerator {
    String getName(HttpServletRequest request, HttpServletResponse response, Object handler);
}
