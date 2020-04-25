package com.zg.moshi.danli;

/**
 * 饿汉式
 *
 * 不管你用不用。项目启动时  就给你实例出来。。
 *
 * 优点：绝对线程安全，能够被反射破坏
 *
 * 缺点：可能造成内存浪费。
 */
public class SingleEHan {

    public static SingleEHan singleEHan = new SingleEHan();
    private SingleEHan(){}
}
