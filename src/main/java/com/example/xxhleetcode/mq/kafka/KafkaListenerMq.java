package com.example.xxhleetcode.mq.kafka;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

/**
 * @Author: elyuan
 * @Date: 2021/5/8 4:05 下午
 */
@Component
@Slf4j
public class KafkaListenerMq {


    @KafkaListener(topics = "#{'${kafka.producer.topic}'}")
    public void listen(String input) {
        log.info("接受到kafka消息: {}" , input);
    }
}
