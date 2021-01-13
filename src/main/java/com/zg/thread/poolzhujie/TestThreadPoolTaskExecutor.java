package com.zg.thread.poolzhujie;

import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.util.concurrent.ListenableFuture;

import java.util.concurrent.Callable;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @ClassName TestThreadPoolTaskExecutor
 * @Description 给线程池增加 监控   https://www.jianshu.com/p/03e4dc249bfb
 * @Author Mads
 * @Date 2020/5/15 18:51
 * @Version 1.0
 */
public class TestThreadPoolTaskExecutor extends ThreadPoolTaskExecutor {
    private void showThreadPoolInfo(String method) {
        ThreadPoolExecutor threadPoolExecutor = getThreadPoolExecutor();

        if (null == threadPoolExecutor) {
            return;
        }

        //这里可以用使用存储到在线库、打日志、存缓存等方式把监控数据记录下来
        //用到线程池里面具体哪个方法执行的线程任务
        System.out.println("使用到的方法：" + method);
        //是我们配置在config里面线程前缀的名字（用于多个线程池中区分线程池）
        System.out.println("线程的前缀名字：" +
                this.getThreadNamePrefix());
        System.out.println("线程池中的任务数量：" +
                threadPoolExecutor.getTaskCount());
        System.out.println("线程池中已经完成的任务数：" +
                threadPoolExecutor.getCompletedTaskCount());
        System.out.println("线程池中的线程数：" +
                threadPoolExecutor.getActiveCount());
        System.out.println("线程池中队列的长度：" +
                threadPoolExecutor.getQueue().size());
    }

    @Override
    public void execute(Runnable task) {
        showThreadPoolInfo("executeRunnable");
        super.execute(task);
    }

    @Override
    public void execute(Runnable task, long startTimeout) {
        showThreadPoolInfo("executeRunnableStartTimeout");
        super.execute(task, startTimeout);
    }

    @Override
    public Future<?> submit(Runnable task) {
        showThreadPoolInfo("submitRunnable");
        return super.submit(task);
    }

    @Override
    public <T> Future<T> submit(Callable<T> task) {
        showThreadPoolInfo("submitCallable");
        return super.submit(task);
    }

    @Override
    public ListenableFuture<?> submitListenable(Runnable task) {
        showThreadPoolInfo("submitListenableRunnable");
        return super.submitListenable(task);
    }

    @Override
    public <T> ListenableFuture<T> submitListenable(Callable<T> task) {
        showThreadPoolInfo("submitListenableCallable");
        return super.submitListenable(task);
    }
}
