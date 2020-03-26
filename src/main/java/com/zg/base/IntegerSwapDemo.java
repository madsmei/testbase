package com.zg.base;

import java.lang.reflect.Field;
import java.util.Arrays;

/*******
 * 为什么基础类型可以直接赋值给引用类型？
 *   知识点：自动装箱、拆箱操作。限制条件是 在int的取值范围内 -128~127之间
 *   Integer的源码里 维护着一个存储 -128~127之间长度是256的一个静态数组（有点缓存的感觉，提高了JVM的效率）
 *   源码里 的自动装箱时 valueOf()方法，如果在范围内则直接在缓存里拿，不会产生新的对象，
 *
 *     1.如果不想让编译器自动装箱。开发者可以自己new Integer(i)来实现。避免 从缓存直接拿数据，
 *
 *
 *  因为 Integer 源码重写了 equese()方法。在比较两个封装类时  可以使用equese()方法
 *
 *   在实际开发中  要特别注意  自动装箱造成的BUG
 */
public class IntegerSwapDemo {

    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException {
        Integer a = 1;

        Integer b = 2;

        swap(a,b);

        System.out.println("a-->"+a+"-->b->"+b);
    }

    /*****
     * 对 两个值进行值互换
     * @param a
     * @param b
     */
    public static void swap(Integer a,Integer b) throws NoSuchFieldException, IllegalAccessException {

        //这里不能使用封装类。
        int temp = a;

        Field value = Integer.class.getDeclaredField("value");
        //默认下 反射是不允许 改变final的值得。修改这个系统的变量后就可以反射改变了。
        // 一切皆可射
        value.setAccessible(true);

        value.set(a, b);
        //这一步很关键。为了防止 自动装箱造成复制不对
        value.set(b, new Integer(temp));
    }
}
