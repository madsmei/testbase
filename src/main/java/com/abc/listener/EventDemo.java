package com.abc.listener;

import org.springframework.context.ApplicationEvent;

/**
 * @Description: 事件
 * @Date 2020/3/8
 * @Version V1.0
 * @Author Mads
 **/

public class EventDemo extends ApplicationEvent {
    private String text;

    public EventDemo(Object source) {
        super(source);
    }

    public EventDemo(Object source, String text) {
        super(source);
        this.text = text;
    }

    public String print() {
        return "DemoEvent-->"+text;
    }
}
