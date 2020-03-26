package com.abc.ratelimit;

import com.google.common.util.concurrent.RateLimiter;

import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;

/**
 * @Description:  令牌桶,这是演示原理和使用。真正项目中一般不会这用。因为这样用  我要在所有的接口了都要加上这样的逻辑。太傻了，
 * 会采用  AOP+注解的方式
 *
 * 单机做限流是没有问题的，但是我们通常是分布式业务处理，，可以使用类似 Redis来处理
 * 使用场景：当请求陡增的时候用令牌桶比较合适，因为它的原理是 在进入时控制流量
 *
 *
 * @Date 2020/2/13
 * @Version V1.0
 * @Author Mads
 **/
public class RateLimiterDemo {

   private static CountDownLatch countDownLatch = new CountDownLatch(40);

    private static RateLimiter rateLimiter = RateLimiter.create(30);

    public static void tesst() {
        for (int i = 0; i < 40; i++) {
            new Thread(()->{

                try {
                    countDownLatch.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                //这就是拿令牌。如果拿不到就会一直等待
                rateLimiter.acquire();

                System.out.println("一个数据库连接逻辑");
            }).start();

            countDownLatch.countDown();
        }

    }

    public static void main(String[] args) {
        tesst();
    }
}
