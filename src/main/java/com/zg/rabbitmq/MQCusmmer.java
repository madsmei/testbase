package com.zg.rabbitmq;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @Description: TODO
 * @Date 2020/3/14
 * @Version V1.0
 * @Author Mads
 **/
@Component
@RabbitListener(queues = "queueName")
public class MQCusmmer {

    @RabbitHandler
    public void getMsg(String msg) {
        System.out.println("收到消息00：" + msg);
    }

}
