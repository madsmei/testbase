package com.zg.thread;

import java.util.concurrent.*;

/**
 * @Description: 比如有100个需要返回结果的线程。每个线程的执行时间是不一样的，
 * 通常线程池在获取结果时的get()方法会阻塞，这就编程了顺序执行，
 * 而CompletionService就可以让先完成的线程先返回结果，不会受加入任务的
 * 顺序影响，提高 利用率，
 * 适用 那些 有好几步操作的线程任务。谁先执行完第一步就先执行第二部。。。，
 * @Date 2020/2/19
 * @Version V1.0
 * @Author Mads
 **/
public class CompletionServiceDemo {

    private static class ThreadTask implements Callable<String> {

        private String name;

        public ThreadTask(String name) {
            this.name = name;
        }

        @Override
        public String call() throws Exception {

            TimeUnit.MILLISECONDS.sleep(ThreadLocalRandom.current().nextInt(10, 100));

            System.out.println("thread name--> " + name);

            return "result:" + name;
        }
    }


    public static void main(String[] args) throws InterruptedException, ExecutionException {

        ExecutorService poolExecutor = Executors.newFixedThreadPool(10);

        CompletionService completionService = new ExecutorCompletionService(poolExecutor);

        //向线程池扔任务
        for (int i = 0; i < 10; i++) {
            completionService.submit(new ThreadTask("mads" + i));
        }

        //取出返回结果
        for (int i = 0; i < 10; i++) {
            System.out.println("第" + i + "个线程返回结果-->" + completionService.take().get());
        }

        //不调用此代码。你知道会发生什么
        poolExecutor.shutdownNow();
    }


}
