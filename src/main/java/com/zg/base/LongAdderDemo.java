package com.zg.base;

import java.util.concurrent.atomic.LongAdder;

/**
 * @Description: LongAdder的基本思路就是分散热点，将value值分散到一个数组中，不同线程会命中到数组的不同槽中，
 * 各个线程只对自己槽中的那个值进行CAS操作，这样热点就被分散了，冲突的概率就小很多。
 * 如果要获取真正的long值，只要将各个槽中的变量值累加返回。这种做法和ConcurrentHashMap中的“分段锁”其实就是类似的思路。
 * LongAdder提供的API和AtomicLong比较接近，两者都能以原子的方式对long型变量进行增减。
 * 但是AtomicLong提供的功能其实更丰富，尤其是addAndGet、decrementAndGet、compareAndSet这些方法。
 * <p>
 * 低并发、一般的业务场景下AtomicLong是足够了。如果并发量很多，存在大量写多读少的情况，那LongAdder可能更合适。
 * @Date 2020/2/24
 * @Version V1.0
 * @Author Mads
 **/
public class LongAdderDemo {

    public static void main(String[] args) {
        LongAdder longAdder = new LongAdder();

        //原子性 +1
        longAdder.increment();
        longAdder.increment();
        longAdder.increment();

        System.out.println(longAdder.longValue());

        //原子性-1
        longAdder.decrement();
        System.out.println(longAdder.longValue());

    }
}
