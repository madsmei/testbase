package com.abc.listener;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

/**
 *  监听器
 **/
@Component
public class ListenerHandlerDemo {

    @EventListener
    public void onApplicationEvent(EventDemo eventDemo) {
        System.out.println("------------------------------");
        System.out.println("ListenerDemo--->" + eventDemo.print());
    }
}
