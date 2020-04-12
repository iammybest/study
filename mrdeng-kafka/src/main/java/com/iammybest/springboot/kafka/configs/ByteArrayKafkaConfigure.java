package com.iammybest.springboot.kafka.configs;

import com.iammybest.springboot.kafka.properties.ByteArrayKafkaProperties;
import com.iammybest.springboot.vo.KafkaEntity;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.KafkaListenerContainerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.listener.ConcurrentMessageListenerContainer;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

/**
 * @DESCRIBE:
 * @TIME: 2020/3/27 16:53
 * @AUTHOR: qinghai.deng
 **/
@Configuration(
        proxyBeanMethods = false
)
@EnableConfigurationProperties({ByteArrayKafkaProperties.class})
@Log4j2
public class ByteArrayKafkaConfigure  extends DengKafkaConfigure{
    @Resource
    ByteArrayKafkaProperties byteArrayKafkaProperties;
    @PostConstruct
    protected void setProperties() {
        super.properties = byteArrayKafkaProperties;
    }
    @Bean(value = "byteArrayKafkaListenerContainerFactory")
    public KafkaListenerContainerFactory<ConcurrentMessageListenerContainer<String, KafkaEntity>> byteArrayKafkaListenerContainerFactory() {
        log.info("Deng|初始化byteArrayKafkaListenerContainerFactory");
        return kafkaListenerContainerFactory();
    }
    @Bean(value = "byteArrayKafkaTemplate")
    public KafkaTemplate<?, ?> byteArrayKafkaTemplate() {
        log.info("Deng|初始化byteArrayKafkaTemplate");
        return super.kafkaTemplate();
    }
}
