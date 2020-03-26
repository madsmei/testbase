package com.zg.single;

/**
 * 线程安全的
 * 懒汉式-类初始化模式
 */
public class SingleInit {
    private SingleInit(){}

    /*****
     * 使用静态内部类
     */
    private static class InstanceHolder{
        public static SingleInit instance = new SingleInit();
    }

    public static SingleInit getInstance(){
        return InstanceHolder.instance;
    }
}
