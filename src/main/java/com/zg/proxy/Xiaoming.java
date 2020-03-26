package com.zg.proxy;

import java.util.concurrent.locks.ReentrantLock;

public class Xiaoming implements People {

    private ReentrantLock lock = new ReentrantLock();
    @Override
    public String zhaoduixiang() {

        System.out.println("我是小明，我要找对象");

        return "你是我的小丫小苹果";
    }
}
