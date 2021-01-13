package com.zg.homework.halfAtomicInt;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * @Description: TODO
 * @Date 2020/2/15
 * @Version V1.0
 * @Author Mads
 **/
public class SelfAtomicIntTest {

    private static HalfAtomicInt atomicInt = new HalfAtomicInt();

    private static CountDownLatch countDownLatch = new CountDownLatch(7);

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 7000; i++) {
            new Thread(() -> {
                try {
                    countDownLatch.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                for (int k = 0; k < 10; k++) {
                    atomicInt.increment();
                }
            }).start();

            countDownLatch.countDown();
        }

        TimeUnit.MILLISECONDS.sleep(2000);
        System.out.println("最后执行结果" + atomicInt.getCount());
    }

}
