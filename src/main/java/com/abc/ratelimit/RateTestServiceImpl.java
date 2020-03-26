package com.abc.ratelimit;

import com.abc.ratelimit.redis.MadsRedislimit;

/**
 * @Description: 模拟 令牌桶的  业务service
 * @Date 2020/2/13
 * @Version V1.0
 * @Author Mads
 **/
public class RateTestServiceImpl {

//    @MadsRatelimit(limit = 30,timeout = 30,fallback = "getErrorMsg")
    @MadsRedislimit(limit = 10,fallback = "getErrorMsg")
    public String getName() {
        return "My name is Mads";
    }


    public String getErrorMsg() {
        //服务降级的业务处理，
        System.out.println("。。。redis限流服务降级了。。。");
        return "";
    }

}
