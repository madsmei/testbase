package com.zg.lock.aqs;

import java.util.Hashtable;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * @Description: 手写 独占锁。支持 可重入
 * 思路l:  请看 {@link SelfLock}
 *
 *  可重入 和 不可重入  的核心思想 就是对  state的操作。入多少次就减多少次，直到为0时才释放锁，
 *
 * @Date 2020/2/16
 * @Version V1.0
 * @Author Mads
 **/
public class SelfReenTrantLock implements Lock {


    //继承 AQS模板,AQS的核心是 维持一个state标志位来标识 线程的状态。
    private static class Sync extends AbstractQueuedSynchronizer {

        /*****
         * 尝试 拿锁
         * 这里看源码 得知，真正拿锁的方法是acquire方法，因为它使用了这个判断。所以我们重写这个方法就可以了
         * @param arg
         * @return
         */
        @Override
        protected boolean tryAcquire(int arg) {

            //如果设置成功 说明抢锁成功，就把当前线程 保存下来
            if(compareAndSetState(0, 1)) {
                setExclusiveOwnerThread(Thread.currentThread());

                return true;
            }else if(getExclusiveOwnerThread() == Thread.currentThread()) {//支持可重入
                setState(getState() + 1);
                return true;
            }
            return false;
        }

        /*****
         *放锁
         * @param arg
         * @return
         */
        @Override
        protected boolean tryRelease(int arg) {

            if(getExclusiveOwnerThread() != Thread.currentThread()) {
                throw new IllegalMonitorStateException();
            }
            if(getState() == 0) {
                throw new IllegalMonitorStateException();
            }
            setState(getState() - 1);
            if(getState() == 0){
                setExclusiveOwnerThread(null);
            }

            return true;
        }

        /*****
         *是否处于占用状态
         * @return
         */
        @Override
        protected boolean isHeldExclusively() {
            return getState() > 0;
        }

        Condition newCondtion(){return new ConditionObject();}
    }

    private static Sync sync = new Sync();

    @Override
    public void lock() {
        sync.acquire(1);
    }

    @Override
    public void lockInterruptibly() throws InterruptedException {
        sync.acquireInterruptibly(1);
    }

    @Override
    public boolean tryLock() {
        return sync.tryAcquire(1);
    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return false;
    }

    @Override
    public void unlock() {
        sync.release(0);
    }

    @Override
    public Condition newCondition() {
        return sync.newCondtion();
    }
}
