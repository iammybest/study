package com.iammybest.springboot.kafka.configs;

import com.iammybest.springboot.kafka.properties.AutoKafkaProperties;
import com.iammybest.springboot.kafka.properties.DengKafkaProperties;
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
@Configuration
@EnableConfigurationProperties({AutoKafkaProperties.class})
@Log4j2
public class AutoKafkaConfigure extends DengKafkaConfigure{
    @Resource
    protected  AutoKafkaProperties autoKafkaProperties;
    @PostConstruct
    protected void setProperties() {
        super.properties = autoKafkaProperties;
    }
    @Bean(value = "autoKafkaListenerContainerFactory")
    public KafkaListenerContainerFactory<ConcurrentMessageListenerContainer<String, KafkaEntity>> autoKafkaListenerContainerFactory() {
        log.info("Deng|初始化autoKafkaListenerContainerFactory");
        return kafkaListenerContainerFactory();
    }
    @Bean(value = "autoKafkaTemplate")
    public KafkaTemplate<?, ?> autoKafkaTemplate() {
        log.info("Deng|初始化autoKafkaTemplate");
        return super.kafkaTemplate();
    }
}
