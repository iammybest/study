package com.iammybest.springboot.kafka.configs;

import com.alibaba.fastjson.JSON;
import com.iammybest.springboot.kafka.properties.DengKafkaProperties;
import com.iammybest.springboot.vo.KafkaEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.KafkaListenerContainerFactory;
import org.springframework.kafka.core.*;
import org.springframework.kafka.listener.ConcurrentMessageListenerContainer;
import org.springframework.kafka.support.LoggingProducerListener;
import org.springframework.kafka.support.ProducerListener;

/**
 * @DESCRIBE:
 * @TIME: 2020/3/27 16:59
 * @AUTHOR: qinghai.deng
 **/
@Slf4j
public abstract class DengKafkaConfigure {

    protected DengKafkaProperties properties;

    /**
     * @PostConstruct 方式写入配置文件
     */
    protected abstract void setProperties();
    public KafkaListenerContainerFactory<ConcurrentMessageListenerContainer<String, KafkaEntity>> kafkaListenerContainerFactory() {
        log.info("Deng|初始化kafkaListenerContainerFactory");
        ConcurrentKafkaListenerContainerFactory container = new ConcurrentKafkaListenerContainerFactory();
        container.setConsumerFactory(kafkaConsumerFactory());
        container.setBatchListener(true);
        return container;
    }
    public KafkaTemplate<?, ?> kafkaTemplate() {
        log.info("Deng|初始化kafkaTemplate");
        KafkaTemplate<Object, Object> kafkaTemplate = new KafkaTemplate(kafkaProducerFactory());
        kafkaTemplate.setProducerListener(kafkaProducerListener());
        kafkaTemplate.setDefaultTopic(this.properties.getTemplate().getDefaultTopic());
        return kafkaTemplate;
    }
    public ProducerListener<Object, Object> kafkaProducerListener() {
        return new LoggingProducerListener();
    }

    public ConsumerFactory<?, ?> kafkaConsumerFactory() {
        log.info("Deng|调用consumer properties|{}", JSON.toJSONString(this.properties.buildConsumerProperties()));
        return new DefaultKafkaConsumerFactory(this.properties.buildConsumerProperties());
    }
    public ProducerFactory<?, ?> kafkaProducerFactory() {
        DefaultKafkaProducerFactory<?, ?> factory = new DefaultKafkaProducerFactory(this.properties.buildProducerProperties());
        String transactionIdPrefix = this.properties.getProducer().getTransactionIdPrefix();
        if (transactionIdPrefix != null) {
            factory.setTransactionIdPrefix(transactionIdPrefix);
        }
        return factory;
    }

}
