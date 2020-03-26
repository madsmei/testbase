package com.zg.homework.gunshoot;

import java.util.concurrent.atomic.AtomicInteger;

/******
 * 烛光VIP课程多线程篇   课后作业  2020-01-20
 *
 * 题目
 * 采用多线程技术，例如wait/notify，设计实现一个符合生产者和消费者问题的程序，
 * 对某一个对象（枪膛）进行操作，其最大容量是20颗子弹，生产者线程是一个压入线程，
 * 它不断向枪膛中压入子弹，消费者线程是一个射出线程，它不断从枪膛中射出子弹。
 */
public class Gun {

    //弹夹最大容量
    public int CLIPS_MAX = 20;
    //当前弹夹子弹数量
    public AtomicInteger CLIPS_COUNT = new AtomicInteger();

    public Gun() {}

    /*****
     * 射击(消费者，等待范式)
     */
    public synchronized void  shoot() throws InterruptedException {

        while (CLIPS_COUNT.intValue() <= 0) {
            System.out.println("shoot()-->弹夹空了，无法射击");

            wait();
        }
        System.out.println("shoot()-->可以射击，有子弹"+CLIPS_COUNT.intValue());
        //将子弹射出。
        CLIPS_COUNT.decrementAndGet();
        System.out.println("shoot()-->射出后弹夹剩余子弹"+CLIPS_COUNT.intValue());

        notifyAll();
    }

    /*****
     * 装弹 （生产者，通知范式）
     */
    public synchronized  void pressIn() throws InterruptedException {

        System.out.println("pressIn()-->准备装弹，当前弹量"+CLIPS_COUNT.intValue());
        while (CLIPS_COUNT.intValue() >= CLIPS_MAX) {
            System.out.println("pressIn()-->弹夹满了");
            wait();
        }

        CLIPS_COUNT.incrementAndGet();
        System.out.println("pressIn()-->装弹成功，当前弹量"+CLIPS_COUNT.intValue());

        notifyAll();
    }

}
