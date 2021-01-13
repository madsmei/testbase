package com.zg.thread.poolzhujie;

import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Component;

import java.util.concurrent.Future;

/**
 * @ClassName ThreadTest
 * @Description 使用@Async("asyncServiceExecutor") 就能吧方法加入到，线程池中。这里可以用自定义的注解在封装一层
 * @Author Mads
 * @Date 2020/5/15 18:55
 * @Version 1.0
 */
@Component
public class ThreadTest {


    @Async("asyncServiceExecutor")
    public void task1() {
        System.out.println("测试一个没有返回值的任务1");
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("测试一个没有返回值的任务2");
    }

    @Async("asyncServiceExecutor")
    public Future<String> task2() {
        System.out.println("测试一个有返回值的任务1");
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("测试一个有返回值的任务2");
        //返回当前线程的线程名称
        return new AsyncResult<>(Thread.currentThread().getName());
    }
}
