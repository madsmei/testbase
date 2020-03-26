package com.zg.thread;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @Description: TODO
 * @Date 2020/2/14
 * @Version V1.0
 * @Author Mads
 **/
public class ReadWriteLock {

    private final ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

    private Lock read = lock.readLock();
    private Lock write = lock.writeLock();

    public void aa() {


        Condition condition = write.newCondition();
        condition.signal();
    }


}
