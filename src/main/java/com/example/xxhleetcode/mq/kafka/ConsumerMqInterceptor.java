package com.example.xxhleetcode.mq.kafka;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerInterceptor;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.redisson.Redisson;
import org.redisson.api.RAtomicLong;
import org.redisson.api.RBlockingQueue;
import org.redisson.api.RedissonClient;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Map;

/**
 * @Author: elyuan
 * @Date: 2021/5/12 2:59 下午
 */
@Slf4j
public class ConsumerMqInterceptor implements ConsumerInterceptor , ApplicationContextAware {
    @Autowired
    private RedissonClient redissonClient;


    @Override
    public ConsumerRecords onConsume(ConsumerRecords records) {
        log.info("该方法在消息返回给 Consumer 程序之前调用。也就是说在开始正式处理消息之前，拦截器会先拦一道，搞一些事情，之后再返回给你");
        log.info("onConsume records:{}",records);
        redissonClient.getLock("");
        applicationContext.getBean("");
        long lantency = 0L;
        Iterator iterator = records.iterator();
        while (iterator.hasNext()){
            ConsumerRecord record = (ConsumerRecord)iterator.next();
            lantency += (System.currentTimeMillis() - record.timestamp());
        }
        RAtomicLong totalLatency1 = redissonClient.getAtomicLong("totalLatency");
        long totalLatency = totalLatency1.addAndGet(lantency);
        long totalSentMsgs = redissonClient.getAtomicLong("totalSentMsgs").get();
        RBlockingQueue<Object> avgLatency = redissonClient.getBlockingQueue("avgLatency");
        avgLatency.add(totalLatency / totalSentMsgs);
        return records;
    }

    @Override
    public void close() {

    }

    @Override
    public void onCommit(Map offsets) {
        log.info("Consumer 在提交位移之后调用该方法。通常你可以在该方法中做一些记账类的动作，比如打日志等");
        log.info("onCommit offsets:{}",offsets);
    }

    @Override
    public void configure(Map<String, ?> configs) {

    }
    /**
     * 上下文对象实例
     */
    private ApplicationContext applicationContext;
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext =applicationContext;
    }
}
