//package com.abc.kafka;
//
//import com.abc.entity.User;
//
//import java.util.concurrent.ExecutionException;
//import java.util.concurrent.TimeUnit;
//import java.util.concurrent.TimeoutException;
//
///**
// * @Description: 生产者
// * @Date 2020/4/20
// * @Version V1.0
// * @Author Mads
// **/
//public class Produder {
//    private final KafkaTemplate<Object, Object> kafkaTemplate;
//
//    public String send(String messge) {
//        kafkaTemplate.send("topic1", "topci1:" + messge);
//        kafkaTemplate.send("topic2", "topci2:" + messge);
//        return messge;
//    }
//
//    // 异步
//    public void sendAnsyc(final User bar) {
////        ProducerRecord<Object, Object> producerRecord = new ProducerRecord<>("ansyc", bar);
//
//        ListenableFuture<SendResult<Object, Object>> future = template.send("ansyc",bar);
//        future.addCallback(new ListenableFutureCallback<SendResult<Object, Object>>() {
//            @Override
//            public void onSuccess(SendResult<Object, Object> result) {
//                System.out.println("发送消息成功：" + result);
//            }
//
//            @Override
//            public void onFailure(Throwable ex) {
//                System.out.println("发送消息失败："+ ex.getMessage());
//            }
//        });
//    }
//
//    // 同步
//    public void sendSync(final User bar) {
//        ProducerRecord<Object, Object> producerRecord = new ProducerRecord<>("sync", bar);
//        try {
//            template.send(producerRecord).get(10, TimeUnit.SECONDS);
//            System.out.println("发送成功");
//        }
//        catch (ExecutionException e) {
//            System.out.println("发送消息失败："+ e.getMessage());
//        }
//        catch (TimeoutException | InterruptedException e) {
//            System.out.println("发送消息失败："+ e.getMessage());
//        }
//    }
//}
