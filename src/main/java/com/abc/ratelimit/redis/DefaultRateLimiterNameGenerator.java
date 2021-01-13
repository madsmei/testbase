package com.abc.ratelimit.redis;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 */
public class DefaultRateLimiterNameGenerator implements IRateLimiterNameGenerator {
    @Override
    public String getName(HttpServletRequest request, HttpServletResponse response, Object handler) {
        throw new NotImplementedException();
    }
}
