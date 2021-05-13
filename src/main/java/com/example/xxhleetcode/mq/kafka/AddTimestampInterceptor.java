package com.example.xxhleetcode.mq.kafka;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.ProducerInterceptor;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.redisson.api.RAtomicLong;
import org.redisson.api.RedissonClient;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @Author: elyuan
 * @Date: 2021/5/12 2:53 下午
 */
@Slf4j
public class AddTimestampInterceptor implements ProducerInterceptor, ApplicationContextAware {

    @Autowired
    private RedissonClient redissonClient;


//    public AddTimestampInterceptor(RedissonClient redissonClient){
//        this.redissonClient=redissonClient;
//    }



    @Override
    public ProducerRecord onSend(ProducerRecord record) {
        System.out.println("sss");
        RAtomicLong redissonClient = this.redissonClient.getAtomicLong("redissonClient");
        long incr = redissonClient.incrementAndGet();
        log.info("消息发送到broker前拦截到消息,record:{},incr:{}",record,incr);
        return record;
    }

    @Override
    public void onAcknowledgement(RecordMetadata metadata, Exception exception) {
        log.info("消息成功提交，metadata:{}",metadata);
        log.error("发送失败",exception);
        /**
         * 该方法会在消息成功提交或发送失败之后被调用。还记得我在上一期中提到的发送回调通知 callback 吗？
         * onAcknowledgement 的调用要早于 callback 的调用。值得注意的是，
         * 这个方法和 onSend 不是在同一个线程中被调用的，因此如果你在这两个方法中调用了某个共享可变对象，
         * 一定要保证线程安全哦。还有一点很重要，这个方法处在 Producer 发送的主路径中，
         * 所以最好别放一些太重的逻辑进去，否则你会发现你的 Producer TPS 直线下降。
         */
    }

    @Override
    public void close() {

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
