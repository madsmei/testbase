package com.abc.listener;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

/**
 * @Description: 监听器
 * @Date 2020/3/8
 * @Version V1.0
 * @Author Mads
 **/
@Component
public class  ListenerHandlerDemo  {

    @EventListener
    public void onApplicationEvent(EventDemo eventDemo) {
        System.out.println("------------------------------");
        System.out.println("ListenerDemo--->"+eventDemo.print());
    }
}
