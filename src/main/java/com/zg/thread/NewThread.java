package com.zg.thread;


/*******
 * 为什么说 线程的实现方式是两种而不是三种？
 *
 * 线程的实现只有两种方式  继承Thread（线程的真正执行者） 和实现Runnable（对任务的抽象）
 * 有返回值的Callable 本身也是包装成Runnable来执行的，
 * 最主要的是 执行者 Thread的构造方法中 并不接受Callable的类型参数
 *
 * 面试官 再说线程的实现方式有3种，就这么使劲怼回去
 */
public class NewThread {

    private static class UseThread extends Thread {
        @Override
        public void run() {
            System.out.println("use Thread ");
        }
    }

    private static  class  UseRunnable implements Runnable {
        @Override
        public void run() {
            System.out.println("i am useRannable");
        }
    }

    public static void main(String[] args) {
        UseThread useThread = new UseThread();
        useThread.start();


        UseRunnable useRunnable = new UseRunnable();
        new Thread(useRunnable).start();
    }
}
