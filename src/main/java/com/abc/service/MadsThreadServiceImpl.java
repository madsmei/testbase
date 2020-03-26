package com.abc.service;

import java.util.concurrent.*;

/*****
 * 多线程版本的多接口远程调用伪代码
 */
public class MadsThreadServiceImpl implements MadsService {
    @Override
    public void hello() {}

    /*******
     * 多进程来执行远程接口拼装必要信息
     * 场景模拟：
     *  用户登陆完后  获取我的页面的信息。页面 有 基本信息。积分信息，订单信息。。。
     *  在微服务场景下。各个信息是进行服务拆分的。在这里 我们一个接口会分别调用各个服务的接口来拼装信息。
     * @throws ExecutionException
     * @throws InterruptedException
     */
    public void byby() throws ExecutionException, InterruptedException {

        //基本信息
        FutureTask<String> uinfo = new FutureTask<String>(()->{
            System.out.println("这里模拟uinfo调用其他API接口");
            return "user信息的结构";
        });

        //订单信息
        FutureTask<String> order = new FutureTask<String>(()->{

            System.out.println("这里模拟order调用其他API接口");

            return "order信息的结构";
        });

//        new Thread(uinfo).start();
//        new Thread(order).start();
        //线程池
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        executorService.submit(uinfo);
        executorService.submit(order);

        System.out.println("返回信息："+uinfo.get()+" "+order.get());

        //使用这个线程池。一定要调用此方法。不然会一直在JVM占用。造成慢慢的吧老年代的空间消耗完最后OOM
        //这里可以吧此方法注释掉，观察一下。其实此时 方法执行完以后 JVM其实并没有回收方法。
        //正常情况下 执行日志最后会打印 Process finished with exit code 0，如果不加此方法。这个日志是出不来的，
//        executorService.shutdownNow();
//        executorService.shutdown();

        //呼叫总台可以关机
        executorService.shutdown();
        //总台：10秒后关机
        if(!executorService.awaitTermination(10, TimeUnit.SECONDS)){
            //总台：执行关机
            executorService.shutdownNow();
        }
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        MadsThreadServiceImpl service = new MadsThreadServiceImpl();
        service.byby();
        System.out.println("--------end---------");
    }
}
