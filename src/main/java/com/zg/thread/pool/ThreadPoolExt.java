package com.zg.thread.pool;

import java.util.concurrent.*;

/**
 * 类说明：扩展线程池的使用范例
 */
public class ThreadPoolExt {
    static class Worker implements Runnable {
        private String taskName;
        private ThreadLocalRandom r = ThreadLocalRandom.current();

        public Worker(String taskName) {
            this.taskName = taskName;
        }

        public String getName() {
            return taskName;
        }

        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName()
                    + " process the task : " + taskName);
            try {
                TimeUnit.MILLISECONDS.sleep(r.nextInt(10, 100));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


    public static void main(String[] args) throws InterruptedException, ExecutionException {

        ExecutorService threadPool = new ThreadPoolExecutor(2, 4, 3,
                TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(10),
                new ThreadPoolExecutor.DiscardOldestPolicy()) {
            @Override
            protected void beforeExecute(Thread t, Runnable r) {
                System.out.println("准备执行..." + ((Worker) r).getName());
            }

            @Override
            protected void afterExecute(Runnable r, Throwable t) {
                System.out.println("执行完成..." + ((Worker) r).getName());
            }

            @Override
            protected void terminated() {
                System.out.println("线程池退出");
            }
        };

        for (int i = 0; i <= 6; i++) {
            Worker worker = new Worker("worker " + i);
            System.out.println("A new task has been added : " + worker.getName());
            threadPool.execute(worker);
        }
        threadPool.shutdown();

    }
}
