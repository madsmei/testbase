package com.zg.thread;

import org.springframework.transaction.annotation.Transactional;

/**
 * @Description: 问： 一个方法里 操作10张表，为了效率我们开启了10个线程，那么怎么保证在一个事务里？
 *              1.方法删使用@Transactional注解，
 *              2.开启一个 守护线程，一直等待。直到子线程和主线程完成
 *
 *              原理：一般情况下。开启子线程后。主线程就结束了，所以我们这里 用守护线程 不让主线程结束一直等到子线程执行完
 * @Date 2020/2/11
 * @Version V1.0
 * @Author Mads
 **/
public class TransationThread {


    @Transactional
    public void testTransation() {
        new Thread(()->{
            System.out.println("处理用户表逻辑");
        }).start();

        new Thread(()->{
            System.out.println("处理积分表逻辑");
        }).start();

        new Thread(()->{
            System.out.println("处理订单表逻辑");
        }).start();


        Thread domaThread = new Thread(()->{
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("开启了主线程的守护线程");
        });
        domaThread.setDaemon(true);
        domaThread.start();


    }
}
