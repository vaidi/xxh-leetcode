package com.example.xxhleetcode.mq.kafka;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * @Author: elyuan
 * @Date: 2021/5/8 4:05 下午
 */
@Component
@Slf4j
public class KafkaListenerMq {


    @KafkaListener(topics = "#{'${kafka.producer.topic}'}",containerFactory = "listenerContainerFactory")
    public void listen(ConsumerRecord<?, ?> record,Acknowledgment ack) throws InterruptedException {
        log.info("接受到kafka消息: record{},ack:{}" , record);
        TimeUnit.MILLISECONDS.sleep(200);
        ack.acknowledge();
        // Acknowledgment ack
    }
}
