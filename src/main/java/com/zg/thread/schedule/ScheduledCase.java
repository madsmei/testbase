package com.zg.thread.schedule;

import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 定时任务 线程池
 * 类说明：演示ScheduledThreadPoolExecutor的用法
 * ScheduledThreadPoolExecutor
 * 使用工厂类Executors来创建。Executors可以创建2种类型的ScheduledThreadPoolExecutor，如下。
 * •ScheduledThreadPoolExecutor。包含若干个线程的ScheduledThreadPoolExecutor。
 * •SingleThreadScheduledExecutor。只包含一个线程的ScheduledThreadPoolExecutor。
 * ScheduledThreadPoolExecutor适用于需要多个后台线程执行周期任务，同时为了满足资源管理的需求而需要限制后台线程的数量的应用场景。
 * SingleThreadScheduledExecutor适用于需要单个后台线程执行周期任务，同时需要保证顺序地执行各个任务的应用场景。
 * 提交定时任务
 * public ScheduledFuture<?> schedule(Runnable command, long delay, TimeUnit unit)
 * //向定时任务线程池提交一个延时Runnable任务（仅执行一次）
 * <p>
 * public <V> ScheduledFuture<V> schedule(Callable<V> callable, long delay, TimeUnit unit);
 * //向定时任务线程池提交一个延时的Callable任务（仅执行一次）
 * <p>
 * public ScheduledFuture<?> scheduleAtFixedRate(Runnable command, long initialDelay,     long period, TimeUnit unit)
 * //向定时任务线程池提交一个固定时间间隔执行的任务
 * <p>
 * public ScheduledFuture<?> scheduleWithFixedDelay(Runnable command, long initialDelay,  long delay, TimeUnit unit);
 * //向定时任务线程池提交一个固定延时间隔执行的任务
 * <p>
 * 固定时间间隔的任务不论每次任务花费多少时间，下次任务开始执行时间从理论上讲是确定的，当然执行任务的时间不能超过执行周期。
 * 固定延时间隔的任务是指每次执行完任务以后都延时一个固定的时间。由于操作系统调度以及每次任务执行的语句可能不同，所以每次任务执行所花费的时间是不确定的，也就导致了每次任务的执行周期存在一定的波动。
 * 定时任务超时问题
 * scheduleAtFixedRate中，若任务处理时长超出设置的定时频率时长，本次任务执行完才开始下次任务，下次任务已经处于超时状态，会马上开始执行。
 * 若任务处理时长小于定时频率时长，任务执行完后，定时器等待，下次任务会在定时器等待频率时长后执行。
 * 如下例子：
 * 设置定时任务每60s执行一次，那么从理论上应该第一次任务在第0s开始,第二次任务在第60s开始，第三次任务在120s开始，但实际运行时第一次任务时长80s，第二次任务时长30s，第三次任务时长50s，则实际运行结果为：
 * 第一次任务第0s开始,第80s结束；
 * 第二次任务第80s开始,第110s结束(上次任务已超时,本次不会再等待60s,会马上开始)；
 * 第三次任务第120s开始,第170s结束.
 * 第四次任务第180s开始.....
 */
public class ScheduledCase {
    public static void main(String[] args) {

        ScheduledThreadPoolExecutor schedule
                = new ScheduledThreadPoolExecutor(1);

        //延时Runnable任务（仅执行一次）
//        schedule.schedule(new Runnable() {
//            public void run() {
//                System.out.println("-----The task only run once!----");
//            }
//        }, 3000, TimeUnit.MILLISECONDS);

        //固定时间间隔执行的任务,第二次任务在6000 ms后执行，第三次在
        //6000*2 ms后执行
        schedule.scheduleAtFixedRate(
                new ScheduleWorkerTime(),
                0, 6000, TimeUnit.MILLISECONDS);

        //固定延时间隔执行的任务
//        schedule.scheduleWithFixedDelay(new Runnable() {
//            public void run() {
//                System.out.println("******FixedDelay:start,"
//                        + ScheduleWorkerTime.formater.format(new Date()));
//                SleepTools.second(2);
//                System.out.println("******FixedDelay:end,"
//                        + ScheduleWorkerTime.formater.format(new Date()));
//            }
//        }, 1000, 3000, TimeUnit.MILLISECONDS);
    }
}
