package com.zg.base;

import org.apache.commons.lang3.StringUtils;

import java.util.StringJoiner;

/******
 * 介绍一些 字符串的拼接方法，
 * StringBuffer（线程安全，sync修饰）  和  StringBuilder
 * 他们俩都是 继承的AbstractStringBuilder，底层都是数组，初始长度16,2倍扩容
 *
 * @author mads
 */
public class StringAppendDemo {

    public static void main(String[] args) {

        //第一种
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("");

        //第二种
        StringBuilder stringBuilder = new StringBuilder("");

        //分隔符
        String delimiter = "-";
        String[] elements = {"张三", "李四", "王五"};

        //第三种
        String.join("-", elements);

        //第四种，是第三种源码实现方式
        StringJoiner joiner = new StringJoiner(delimiter);
        for (CharSequence cs: elements) {
            joiner.add(cs);
        }

        //第五种 底层也是使用StringBuilder
        StringUtils.join(elements);

        //第六种  在编译时也是生成 StringBuilder对象的append方法
        String a = "a" + "b";
    }
}
