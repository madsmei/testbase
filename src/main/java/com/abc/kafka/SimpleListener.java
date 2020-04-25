package com.abc.kafka;

import com.abc.entity.User;
import org.springframework.stereotype.Component;

/**
 * @Description: TODO
 * @Date 2020/4/20
 * @Version V1.0
 * @Author Mads
 **/
@Component
public class SimpleListener {
    @KafkaListener(topics = {"topic1", "topic2"})
    public void listen1(String data) {
        System.out.println(data);
    }

    @KafkaListener(topics = "ansyc")
    public void listenAnsyc(User bar) {
        System.out.println(bar);
    }

    @KafkaListener(topics = "sync")
    public void listenSync(User bar) {
        System.out.println(bar);
    }
}
