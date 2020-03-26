package com.zg.forkjoin;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.Future;
import java.util.concurrent.RecursiveTask;
/******
 * fork( ForkJoinTask)	异步执行一个线程
	join( ForkJoinTask)	等待任务完成并返回执行结果
	execute( ForkJoinTask)	执行不带返回值的任务
	submit( ForkJoinTask)	执行带返回值的任务
	invoke( ForkJoinTask)	执行指定的任务，等待完成，返回结果。
	invokeAll(ForkJoinTask)	执行指定的任务，等待完成，返回结果。
	shutdown()	执行此方法之后，ForkJoinPool 不再接受新的任务，但是已经提交的任务可以继续执行。如果希望立刻停止所有的任务，可以尝试 shutdownNow() 方法。
	awaitTermination(int, TimeUnit.SECONDS)	阻塞当前线程直到 ForkJoinPool 中所有的任务都执行结束。
	compute（）	执行任务的具体方法
	Runtime.getRuntime().availableProcessors()	获取CPU个数的方法
	
	
	RecursiveAcion 代表没有返回值
	RecursiveTask	代表有泛型返回值的任务
 * @author wenmeishuai
 *
 */
public class ForkJoinTask extends RecursiveTask<Integer>{

	 //将每个小任务，最多只能累加2个数,使用Fork／Join框架首先要考虑到的是如何分割任务，如果我们希望每个子任务最多执行两个数的相加，那么我们设置分割的阈值是2，  
    private static final int threshold = 2;  
    private int start;//开始  
    private int end;//  
    //累加从start到end之间的数  
      
    public ForkJoinTask() {}
      
    //累加从start到end的数组元素
    public ForkJoinTask(int start, int end) {  
        super();  
        this.start = start;  
        this.end = end;  
    }  

    @Override  
    protected Integer compute() {  
        int sum=0;  
        //当end与start之间的差小于threshold，开始进行累加  
        //如果任务足够小就计算任务
        boolean canCompute = (end - start) <= threshold;
        if(canCompute){
            for (int i=start; i<=end; i++) {
                sum += i;
            }
        }else{  
        	System.out.println("拆分子任务--start:"+start+" end:"+end);
        	// 如果任务大于阈值，就分裂成两个子任务计算
            int middle = (start + end)/2;
            ForkJoinTask leftTask = new ForkJoinTask(start, middle);
            ForkJoinTask rightTask = new ForkJoinTask(middle+1, end);
            
            // 执行子任务
//            leftTask.fork();
//            rightTask.fork();
            //使用 invokeAll 可以避免不必要的fork操作，提高效率
            invokeAll(leftTask, rightTask);
            
            //等待任务执行结束合并其结果
            int leftResult = leftTask.join();
            int rightResult = rightTask.join();
            
            //合并子任务
            sum = leftResult + rightResult;
        }  
           
        return sum;
    }  
    public static void main(String[] args){
        ForkJoinPool forkjoinPool = new ForkJoinPool();
        
        //生成一个计算任务，计算1+2+3+4
        ForkJoinTask task = new ForkJoinTask(1, 100);
        
        //执行一个任务
        Future<Integer> result = forkjoinPool.submit(task);
        
        try{
            System.out.println(result.get());
        }catch(Exception e){
            System.out.println(e);
        }
    }
}
