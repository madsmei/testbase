package com.zg.single;

/**
 * 懒汉式-双重检查
 */
public class SingleDcl {
    private volatile static SingleDcl singleDcl;
    private SingleDcl(){
    }

    public static SingleDcl getInstance(){
        if (null == singleDcl){ //第一次检查，不加锁
            System.out.println(Thread.currentThread()+" is null");
            synchronized(SingleDcl.class){ //加锁
                if (null == singleDcl){ //第二次检查，加锁情况下
                    System.out.println(Thread.currentThread()+" is null");
                    //1、开辟内存空间
                    //2、对象的实例化
                    //3、引用和和内存空间实例挂钩
                    singleDcl = new SingleDcl();
                }
            }
        }
        return singleDcl;
    }
}
