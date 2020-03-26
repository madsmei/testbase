package com.zg.proxy;

import java.lang.reflect.Proxy;

/**
 * @Description:
 * @Date 2020/2/10
 * @Version V1.0
 * @Author Mads
 **/
public class ProxyTest {

    public static void main(String[] args) {
        People people = (People) Proxy.newProxyInstance(ProxyTest.class.getClassLoader(),
                new Class[]{People.class},
                new Parent(new Xiaoming()));

        String result = people.zhaoduixiang();

        System.out.println(result);
    }

}
