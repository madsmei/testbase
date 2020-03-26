package com.zg.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;


/*****
 * 代理类的增强类。
 */
public class Parent implements InvocationHandler {

    private People people;

    public Parent(People people) {
        this.people = people;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        before();

        Object result = method.invoke(people, args);

        after();
        //如果确定代理类没有 返回值。这里就可以不返回
        return result;
    }

    /*
     *  这个方法是小明在找到对象之前，父母帮助他做得事情
     * */
    private void before() {
        System.out.println("我是小明的父母，我需要帮助小明找对象！！");
    }

    /*
     * 找到对象之前，父母帮助他操持婚礼，带小孩
     * */
    private void after() {
        System.out.println("我是小明的父母，我们需要帮助小明操持婚礼，帮他带小孩");
    }
}
