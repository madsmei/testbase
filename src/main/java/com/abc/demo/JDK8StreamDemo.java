package com.abc.demo;

import org.apache.commons.lang3.StringUtils;

import javax.sound.midi.Soundbank;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/*******
 * jdk1.8中年的Stream的用法
 */
public class JDK8StreamDemo {

    public static void main(String[] args) {
        List<String> list = new ArrayList<>(10);
        list.add("mads1");
        list.add("mads2");
        list.add("mads3");
        list.add("mads4");
        list.add("mads5");
        list.add("xads6");
        list.add("1234567");

        System.out.println(list.stream().filter(i->i.equals("mads3")).findFirst().get());


        System.out.println("第一个元素："+ list.stream().findFirst().get());
        System.out.println("????："+list.stream().findAny().get());
        //必须所有的都要满足条件才返回true。
        boolean m = list.stream().allMatch(s -> s.startsWith("m"));
        System.out.println("---1--"+m);
        //相当于 上面条件上面加了一个 ！，做了一个结果反转
        boolean m1 = list.stream().noneMatch(s -> s.startsWith("x"));
        System.out.println("-2--"+m1);

        //只要有一个满足条件就返回true
        boolean m2 = list.stream().anyMatch(s -> s.startsWith("x"));
        System.out.println("-3--"+m2);

        //找出 数字类型的
        String result = list.stream().filter(i-> StringUtils.isNumeric(i))
        //长度>4的
        .filter(i->i.length()>4)
         //转换成小写
        .map(i->i.toLowerCase())
                //排重
        .distinct()
                //排序
        .sorted(Comparator.naturalOrder())
                //增加分隔符
        .collect(Collectors.joining("-幽灵-"));
        System.out.println("--4-"+result);
    }

}
