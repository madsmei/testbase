package com.zg.thread;

import javax.xml.bind.SchemaOutputResolver;
import java.lang.management.ManagementFactory;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;


/******
 * 为什么说java天生就是多线程？
 *   就本类 一个main方法会产生6个线程，1个主线程5个守护线程，
 *      6  Monitor Ctrl-Break RUNNABLE
 *      5  Attach Listener RUNNABLE
 *      4  Signal Dispatcher RUNNABLE
 *      3  Finalizer WAITING  守护线程
 *      2  Reference Handler WAITING
 *      1  main RUNNABLE  主线程，也是我们的业务线程
 *
 * ps：守护线程 依托主线程存活，当主线程 执行完或者退出后。不管守护线程有没有执行完立即退出。因此不要在守护线程里干一些IO的操作
 *
 */
public class OnlyMain {

    public static void main(String[] args) {
        //这个方法一般用不到。如果公司技术团队很大需要公司自己写监控时可以使用此工具来获取运行状态
        //JVM系统管理接口
        ThreadMXBean threadMXBean = ManagementFactory.getThreadMXBean();
        //不需要获取同步的monitor和synchronizer信息，仅仅获取线程和线程堆栈信息
        ThreadInfo[] threadInfos = threadMXBean.dumpAllThreads(false, false);
        // 遍历线程信息，仅打印线程ID和线程名称信息
        for (ThreadInfo threadInfo : threadInfos) {
            System.out.println(threadInfo.getThreadId()+"  "+threadInfo.getThreadName() + " "+threadInfo.getThreadState());
        }
    }
}
