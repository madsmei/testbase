package com.zg.thread;

import java.util.concurrent.*;


/******
 *手写FuturaTask  了解原理    等待通知模式
 * 这里只是很基础的，源码里 是有个status标志位的。只有抢到了标志位线程才能真正的执行run（）方法，因为多线程同时执行run没有什么意义只会
 * 浪费计算机资源，get()方法时阻塞的，
 * @param <V>
 */
public class FuturaTaskTest<V> implements  Runnable, Future<V> {

    private Callable<V> callable;//封装业务逻辑
    private V result = null;//执行结果

    public FuturaTaskTest(Callable<V> callable){
        this.callable = callable;
    }

    /*****
     * 重写父类
     */
    @Override
    public void run() {

        //这里为什么要使用变量呢，是因为避免多线程同时调用run方法时的竞争，当然
        V temp = null;
        try {
            //调用执行业务逻辑
           temp = callable.call();
           synchronized (this){
               result = temp;
               this.notifyAll();
           }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /******
     *获取返回值
     */
    @Override
    public V get() throws InterruptedException, ExecutionException {

        if(null != result){
            return result;
        }
        System.out.println("result--"+result);

        synchronized (this){
            this.wait();
        }
        return result;
    }

    @Override
    public V get(long timeout, TimeUnit unit) throws InterruptedException, ExecutionException, TimeoutException {
        return null;
    }
    @Override
    public boolean cancel(boolean mayInterruptIfRunning) {
        return false;
    }

    @Override
    public boolean isCancelled() {
        return false;
    }

    @Override
    public boolean isDone() {
        return false;
    }




}
