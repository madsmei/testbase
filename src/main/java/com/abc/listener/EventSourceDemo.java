package com.abc.listener;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;

/**
 * @Description: 事件源（事件触发点）
 * @Date 2020/3/8
 * @Version V1.0
 * @Author Mads
 **/
@Service
public class EventSourceDemo  implements LoginService,ApplicationContextAware {

    private ApplicationContext context;

    @Override
    public void Login(String name,String pwd) {

        //这里 为什么会阻塞后面的 ，待确认，，，，
        context.publishEvent(new EventDemo(this, "登陆成功的监听"));
        System.out.println("登陆成功，发送事件给监听处理。。。");
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.context = context;
    }

}
