package com.zg.forkjoin;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;

/*****
 * 多线程
 * 
 * .使用Callable+FutureTask获取执行结果
 * 
 * 示例：http://www.importnew.com/17572.html
 * @author wenmeishuai
 */
public class CallableFutureTask {
	
//	public static TransactionTemplate template;
	
	public static void main(String[] args) {
		
//		template.execute(new TransactionCallback<Object>() {
//
//			@Override
//			public Object doInTransaction(TransactionStatus arg0) {
//				// TODO Auto-generated method stub
//				return null;
//			}
//			
//			
//		});
		 //第一种方式
//        ExecutorService executor = Executors.newCachedThreadPool();
//        CallableFuture task = new CallableFuture();
//        FutureTask<Integer> futureTask = new FutureTask<Integer>(task);
//        executor.submit(futureTask);
//        executor.shutdown();
          
        //第二种方式，注意这种方式和第一种方式效果是类似的，只不过一个使用的是ExecutorService，一个使用的是Thread
        /*Task task = new Task();
        FutureTask<Integer> futureTask = new FutureTask<Integer>(task);
        Thread thread = new Thread(futureTask);
        thread.start();*/
        
        ForkJoinPool pool = new ForkJoinPool(5);//开启5个线程
        ForkJoinTask task2 = new ForkJoinTask(0, 10);
        
        pool.invoke(task2);
          
          
        System.out.println("主线程在执行任务");
        
        try {
            System.out.println("task运行结果"+task2.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
          
        System.out.println("所有任务执行完毕");
	}

}
