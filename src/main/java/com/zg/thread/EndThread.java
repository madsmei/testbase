package com.zg.thread;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/******
 * 优雅的 中断线程的方式。
 *
 * 1.线程内根据 标志位 来进行线程讹结束逻辑
 * 2.调用方 调用设置结束标志位（thread.interrupt()）
 *      1）中的判断标志位有两种方式 一种是在继承Thread方式的线程中直接使用isInterrupted()方法
 *      另外一种是使用静态的方法Thread.currentThread().isInterrupted()，他们的作用都是判断标志位是否已经设置
 *      区别是  后者 在返回后 会将标志位复位
 *
 */
public class EndThread {

    private static class UseRunnable implements Runnable {

        @Override
        public void run() {

            AtomicInteger num = new AtomicInteger();

            //如果收到结束通知 就退出循环
            while (!Thread.currentThread().isInterrupted()) {
                System.out.println("一直快乐的线程 "+num.getAndIncrement());
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(new UseRunnable());

        thread.start();

        TimeUnit.MILLISECONDS.sleep(50);

        //告诉线程 要结束了
        thread.interrupt();
    }
}
