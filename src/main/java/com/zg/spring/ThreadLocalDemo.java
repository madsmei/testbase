package com.zg.spring;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;


/******
 * ThreadLocal  因为它使用了ThreadMap,它是ThreadLocal的内部类,
 * 我们知道  内部类持有外部类 ，内部类的生命周期大于外部类的，容易引起内存泄漏，所以使用了弱引用来毕淼内存泄漏。。
 * @author mads
 */
public class ThreadLocalDemo {


    public static void main(String[] args) {
        ThreadLocal<String> threadLocal = new ThreadLocal<>();

        threadLocal.set("Mads threadLocal value");

        threadLocal.get();


    }
}
