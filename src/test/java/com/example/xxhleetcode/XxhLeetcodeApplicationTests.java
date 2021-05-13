package com.example.xxhleetcode;

import com.example.xxhleetcode.mybatis.service.User;
import com.example.xxhleetcode.mybatis.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.concurrent.ListenableFuture;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

@SpringBootTest
@RunWith(SpringRunner.class)
@Slf4j
public class XxhLeetcodeApplicationTests {


    @Autowired
    private UserService userService;

    @Autowired
    @Qualifier("kafkaTemplate1")
    private KafkaTemplate kafkaTemplate;



    @Autowired
    private RedissonClient redissonClient;

    @Value("${kafka.producer.topic}")
    private String kafkaTopic;



    @Test
    public void testRedisLock() throws InterruptedException {
        String lockStr = "123";
        CountDownLatch countDownLatch = new CountDownLatch(10);
        IntStream.rangeClosed(1,10).forEach(e->{
            new Thread(()->{
                RLock lock = redissonClient.getLock(lockStr);
                lock.lock();
                lock.lock();
                try {
                    countDownLatch.countDown();
                    System.out.println("线程:"+Thread.currentThread().getName()+",count:"+
                            countDownLatch.getCount());
                    TimeUnit.SECONDS.sleep(30);
                    lock.unlock();
                    TimeUnit.SECONDS.sleep(10);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }finally {
                    lock.unlock();
                }
            },"i:"+e).start();
        });
        countDownLatch.await();
    }





    @Test
    public void contextLoads() {
        IntStream.range(1,1000).forEach(e->{
            try {
                System.out.println("开始发送mq消息");
                TimeUnit.MILLISECONDS.sleep(1);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
            kafkaTemplate.send(kafkaTopic, "消息:::" + e);
            //kafkaTemplate.send(kafkaTopic,3,"key","消息:"+e);
//            ListenableFuture send = kafkaTemplate.send(kafkaTopic, "消息:::" + e);
//            try {
//                Object o = send.get();
//                System.out.println("o:"+o.toString());
//            } catch (InterruptedException ex) {
//                ex.printStackTrace();
//            } catch (ExecutionException ex) {
//                ex.printStackTrace();
//            }
        });
    }










}
