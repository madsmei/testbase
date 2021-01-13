package com.zg.lock.aqs;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * @Description: 手写 独占锁。不支持 可重入
 * 思路l:
 * java中 所有的锁都是要实现Lock顶级接口的这个没得说。
 * 既然是锁 我们就需要一些保持锁的状态的一些东西，查看源码得知锁都是基于AQS这套抽象模板来实现的，所以我们显现一个内部类继承AQS
 * 并重写它里面的一些基础方法，就能做到我们自己的独占锁
 * @Date 2020/2/16
 * @Version V1.0
 * @Author Mads
 **/
public class SelfLock implements Lock {


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
            if (compareAndSetState(0, 1)) {
                setExclusiveOwnerThread(Thread.currentThread());

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

            if (getExclusiveOwnerThread() != Thread.currentThread()) {
                throw new IllegalMonitorStateException();
            }
            if (getState() == 0) {
                throw new IllegalMonitorStateException();
            }

            setExclusiveOwnerThread(null);
            setState(0);

            return true;
        }

        /*****
         *是否处于占用状态
         * @return
         */
        @Override
        protected boolean isHeldExclusively() {
            return getState() == 1;
        }

        Condition newCondtion() {
            return new ConditionObject();
        }
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
