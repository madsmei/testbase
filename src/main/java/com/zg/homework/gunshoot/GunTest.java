package com.zg.homework.gunshoot;

import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

/******
 * 枪的测试 类
 */
public class GunTest {

    //枪的对象
   private static Gun gun = new Gun();
    //获取一个随机数生成器
    private static ThreadLocalRandom random =  ThreadLocalRandom.current();


    /*****
     * 射击 线程
     */
    private static class shootThread extends Thread {
        @Override
        public void run() {
            try {
                while (!Thread.currentThread().isInterrupted()){
                    gun.shoot();

                    TimeUnit.MILLISECONDS.sleep(random.nextInt(100, 300));
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /******
     * 装弹 线程
     */
    private static class pressInThread extends Thread {
        @Override
        public void run() {
            try {
                while (!Thread.currentThread().isInterrupted()){
                    gun.pressIn();

                    TimeUnit.MILLISECONDS.sleep(random.nextInt(100, 200));
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {

        for(int i=0;i<5;i++){
            new pressInThread().start();
            new shootThread().start();
        }
    }
}
