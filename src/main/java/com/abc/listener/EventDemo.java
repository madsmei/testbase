package com.abc.listener;

import org.springframework.context.ApplicationEvent;

/**
 *  事件
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
        return "DemoEvent-->" + text;
    }
}
