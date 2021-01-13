package com.zg.util;

import com.google.common.base.Charsets;
import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnels;
import org.apache.commons.lang3.CharSet;

/**
 * @Description: 布隆过滤器
 * @Date 2020/3/24
 * @Version V1.0
 * @Author Mads
 **/
public class BloomFilterDemo {

    public static void main(String[] args) {
        //创建字符串布隆过滤器，使用编码UTF-8
        //创建时需要传入四个参数，但我们只要关心前三个就行
        //Funnel，这是Guava中定义的一个接口，它和PrimitiveSink配套使用，主要是把任意类型的数据转化成Java基本数据类型（primitive value，如char，byte，int……），默认用java.nio.ByteBuffer实现，最终均转化为byte数组
        //expectedInsertions 期望插入数据数，int或long
        //fpp期望误判率，比如1E-7（千万分之一）
        //Strategy 策略，默认选取64位哈希映射函数，BloomFilterStrategies.MURMUR128_MITZ_64
        BloomFilter<CharSequence> bloomFilter = BloomFilter.create(Funnels.stringFunnel(Charsets.UTF_8),
                200000, 1E-7);

        bloomFilter.put("mads");

        boolean mads = bloomFilter.mightContain("mads1");
        System.out.println("过滤器验证结果：" + mads);
    }
}
