package com.zg.thread.schedule;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 *类说明：定时任务的工作类
 */
public class ScheduleWorkerTime implements Runnable{
    public final static int Long_8 = 8;//工作8秒
    public final static int Short_2 = 2;//工作2秒
    public final static int Normal_5 = 5;//工作5秒

    public static SimpleDateFormat formater = new SimpleDateFormat(
            "yyyy-MM-dd HH:mm:ss");
    public static AtomicInteger count = new AtomicInteger(0);
    
    @Override
    public void run() {
    	if(count.get()==0) {
            System.out.println("Long_8....begin:"+formater.format(new Date()));
            try {
                TimeUnit.SECONDS.sleep(Long_8);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Long_8....end:"+formater.format(new Date()));
            count.incrementAndGet();
    	}else if(count.get()==1) {
    		System.out.println("Short_2 ...begin:"+formater.format(new Date()));
            try {
                TimeUnit.SECONDS.sleep(Short_2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
    		System.out.println("Short_2 ...end:"+formater.format(new Date()));
            count.incrementAndGet();
    	}else {
    		System.out.println("Normal_5...begin:"+formater.format(new Date()));
            try {
                TimeUnit.SECONDS.sleep(Normal_5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
    		System.out.println("Normal_5...end:"+formater.format(new Date()));
    		count.incrementAndGet();
    	}
    }
}
