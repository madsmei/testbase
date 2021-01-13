package com.abc;

import com.zg.thread.poolzhujie.ThreadTest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

@RunWith(SpringRunner.class)
@SpringBootTest
//@WebAppConfiguration
public class TestbaseApplicationTests {


    @Autowired
    private ThreadTest testTask;

    @Test
    public void testVoid() {
        //测试没有返回值的任务
        testTask.task1();
        try {
            System.out.println("正在测试没有返回值的任务task1");
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testVoid1() {
        Future<String> future = testTask.task2();
        System.out.println("正在测试有返回值的任务task2");
        while (true) {
            //while true中判断任务是否完成
            if (future.isDone()) {
                System.out.println("task2任务已经完成");
                try {
                    System.out.println("task2任务已经完成，返回值为：" + future.get());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
                break;
            }
            System.out.println("task2任务没有完成正在等待");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


}
