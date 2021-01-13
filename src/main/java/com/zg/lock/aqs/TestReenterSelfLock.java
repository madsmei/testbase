package com.zg.lock.aqs;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 类说明：
 */
public class TestReenterSelfLock {

    static final Lock lock = new ReentrantLock();

    public void reenter(int x) {
        lock.lock();
        try {
            System.out.println(Thread.currentThread().getName() + ":递归层级:" + x);
            int y = x - 1;
            if (y == 0) return;
            else {
                reenter(y);
            }
        } finally {
            lock.unlock();
        }

    }

    public void test() {
        class Worker extends Thread {
            public void run() {
                System.out.println(Thread.currentThread().getName());
                TimeUnit.MILLISECONDS.toSeconds(100);

                reenter(3);
            }
        }
        // 启动3个子线程
        for (int i = 0; i < 3; i++) {
            Worker w = new Worker();
            w.start();
        }
        // 主线程每隔1秒换行
        for (int i = 0; i < 100; i++) {
            TimeUnit.MILLISECONDS.toSeconds(1000);
        }
    }

    public static void main(String[] args) {
        TestReenterSelfLock testMyLock = new TestReenterSelfLock();
        testMyLock.test();
    }
}
