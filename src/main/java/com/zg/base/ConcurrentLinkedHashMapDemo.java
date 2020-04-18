package com.zg.base;

import com.googlecode.concurrentlinkedhashmap.ConcurrentLinkedHashMap;
import com.googlecode.concurrentlinkedhashmap.Weighers;

/**
 * @Description:
 * ConcurrentLinkedHashMap 是google团队提供的一个容器。它有什么用呢？其实它本身是对ConcurrentHashMap的封装，
 * 可以用来实现一个基于LRU策略的缓存。  很适合 用来 做缓存数据借故偶
 *
 * LRU（Least recently used，最近最少使用）算法根据数据的历史访问记录来进行淘汰数据，淘汰掉最不经常使用的数据。
 * @Date 2020/4/17
 * @Version V1.0
 * @Author Mads
 **/
public class ConcurrentLinkedHashMapDemo {
    public static void main(String[] args) {
        /*****
         * 因为我们定义最大容量为 3 ，所以 ConcurrentLinkedHashMap 只能存储 3 个，当我们 put 第四个值时，我们最近最少使用的 1 就会被丢弃了。
         */
        ConcurrentLinkedHashMap<String, String> map = new ConcurrentLinkedHashMap.Builder<String, String>()
                .maximumWeightedCapacity(3).weigher(Weighers.singleton()).build();

        map.put("1", "1");
        map.put("2", "2");
        map.put("3", "3");
        map.put("4", "4");

        map.forEach((k, v) -> System.out.println(k + "," + v));
    }
}
