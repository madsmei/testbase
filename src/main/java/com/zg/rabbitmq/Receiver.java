package com.zg.rabbitmq;

import com.abc.service.MadsService;
import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.listener.api.ChannelAwareMessageListener;
import org.springframework.stereotype.Component;

/**
 * @Description: 消费者确认模式
 * @Date 2020/3/14
 * @Version V1.0
 * @Author Mads
 **/
@Component
public class Receiver implements ChannelAwareMessageListener {

    @Override
    public void onMessage(Message message, Channel channel) throws Exception {
        String msg = new String(message.getBody());
        System.out.println("收到消息体："+msg);

        try {
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
            System.out.println("消息已消费");

        }catch (Exception e ){
             //reject 是原生AMQP协议提供的实现。每次只能拒绝一个消息 第二个参数表示 消息会不会重发。如果有死信队列在false时会进入死信队列
//            channel.basicReject(message.getMessageProperties().getDeliveryTag(), false);
            //Nack 是RabbitMQ对AMQP协议的扩展实现。一次可以支持拒绝多个消息
            channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, true);
            System.out.println("业务出现异常，消息已拒绝");
        }
    }
}
