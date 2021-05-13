package com.example.xxhleetcode.mq.config;

import com.example.xxhleetcode.mq.kafka.AddTimestampInterceptor;
import com.example.xxhleetcode.mq.kafka.ConsumerMqInterceptor;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: elyuan
 * @Date: 2021/5/12 4:16 下午
 */

@Configuration
@EnableConfigurationProperties(KafkaProperties.class)
public class KafkaConfig {

    private final KafkaProperties properties;


    public KafkaConfig(KafkaProperties properties) {
        this.properties = properties;
    }



    private Map<String, Object> consumerProperties() {
        Map<String, Object> props = new HashMap<>();
        props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, properties.getConsumer().getEnableAutoCommit());
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG,properties.getConsumer().getKeyDeserializer());
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, properties.getConsumer().getValueDeserializer());
        props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG,properties.getConsumer().getAutoOffsetReset());
        props.put(ConsumerConfig.MAX_POLL_RECORDS_CONFIG,properties.getConsumer().getMaxPollRecords());
        props.put(ConsumerConfig.GROUP_ID_CONFIG,properties.getConsumer().getGroupId());
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, properties.getBootstrapServers());
        List<Object> interceptors = new ArrayList<>();
        //interceptors.add("com.example.xxhleetcode.mq.kafka.ConsumerMqInterceptor");
        interceptors.add(getConsumerInterceptor());
        props.put(ConsumerConfig.INTERCEPTOR_CLASSES_CONFIG,interceptors);
        return props;
    }




    @Bean("listenerContainerFactory")
    public ConcurrentKafkaListenerContainerFactory listenerContainerFactory() {
        //指定使用DefaultKafkaConsumerFactory
        ConcurrentKafkaListenerContainerFactory factory = new ConcurrentKafkaListenerContainerFactory();
        factory.setConsumerFactory(new DefaultKafkaConsumerFactory(consumerProperties()));
        //设置消费者ack模式为手动，看需求设置
        factory.getContainerProperties().setAckMode(properties.getListener().getAckMode());
        factory.getContainerProperties().setPollTimeout(properties.getListener().getPollTimeout().toMillis());
        //设置可批量拉取消息消费，拉取数量一次3，看需求设置
        factory.setConcurrency(properties.getListener().getConcurrency());
        //factory.setBatchListener(true);
        return factory;
    }

    private Map<String, Object> producerProperties() {
        Map<String, Object> props = new HashMap<>();
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, properties.getProducer().getKeySerializer());
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,properties.getProducer().getValueSerializer());
        props.put(ProducerConfig.ACKS_CONFIG, properties.getProducer().getAcks());
        props.put(ProducerConfig.BATCH_SIZE_CONFIG, properties.getProducer().getBatchSize());
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, properties.getBootstrapServers());
        props.put(ProducerConfig.BUFFER_MEMORY_CONFIG,properties.getProducer().getBufferMemory());
        List<Object> interceptors = new ArrayList<>();
       // interceptors.add("com.example.xxhleetcode.mq.kafka.AddTimestampInterceptor");
        interceptors.add(getProduceInterceptor());
        props.put(ProducerConfig.INTERCEPTOR_CLASSES_CONFIG, interceptors);
        return props;
    }


    @Bean
    public AddTimestampInterceptor getProduceInterceptor(){
        return new AddTimestampInterceptor();
    }

    @Bean
    public ConsumerMqInterceptor getConsumerInterceptor(){
        return new ConsumerMqInterceptor();
    }
    /**
     * 不使用spring boot的KafkaAutoConfiguration默认方式创建的KafkaTemplate，重新定义
     *
     * @param
     * @return
     */
    @Bean("kafkaTemplate1")
    public KafkaTemplate kafkaTemplate() {
        return new KafkaTemplate(new DefaultKafkaProducerFactory(producerProperties()));
    }
}