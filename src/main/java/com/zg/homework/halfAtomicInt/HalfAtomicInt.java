package com.zg.homework.halfAtomicInt;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 类说明：有一个残缺AtomicInteger的类实现了线程安全的：
 * get方法和compareAndSet()方法
 * 用原子变量的自旋思想自行实现它的递增方法集约化科技哈哈
 */
public class HalfAtomicInt {
    private AtomicInteger atomicI = new AtomicInteger(0);


    public void increment() {
        int oldValue = getCount();

        for (; ; ) {
            synchronized (atomicI) {
                //预期值是否等于 内存值
                if (oldValue == getCount()) {
                    System.out.println("比较一致进行相加");
                    atomicI.set(oldValue + 1);
                    break;
                } else {
                    oldValue = getCount();
                    System.out.println("值不一样，等待下次循环");
                }
            }
        }
    }

    public int getCount() {
        return atomicI.get();
    }

    public boolean compareAndSet(int oldValue, int newValue) {
        return atomicI.compareAndSet(oldValue, newValue);
    }


}
