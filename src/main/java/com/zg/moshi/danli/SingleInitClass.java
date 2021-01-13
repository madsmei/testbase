package com.zg.moshi.danli;

import java.lang.reflect.InvocationTargetException;

/**
 * 静态内部类实现方式,属于 懒加载的范畴
 * 利用类加载机制。内部类 初始化时不会加载。只有在使用时 才实例化
 * 线程安全的
 * <p>
 * 缺点：能够被反射破坏
 * <p>
 * 优点：代码优雅，可读性高
 */
public class SingleInitClass {
    private SingleInitClass() {
        //防止 被反射创建对象
        if (null != InstanceHolder.instance) {
            throw new RuntimeException("非法创建对象");
        }
    }

    /*****
     * 使用静态内部类
     */
    private static class InstanceHolder {
        public final static SingleInitClass instance = new SingleInitClass();
    }

    public static SingleInitClass getInstance() {
        return InstanceHolder.instance;
    }


    public static void main(String[] args) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        SingleInitClass.class.getDeclaredConstructor().newInstance();
    }
}
