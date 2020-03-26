package com.zg.rabbitmq;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;

/**
 * @Description: TODO
 * @Date 2020/3/14
 * @Version V1.0
 * @Author Mads
 **/
public class MQProducer {

    @Autowired
    private RabbitTemplate template;

    public void send() {
        template.convertAndSend("exchange", "mads.shuai", "mssage");
    }
}
