package com.iammybest.springboot.kafka.configs;

import com.iammybest.springboot.kafka.properties.EntityKafkaProperties;
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
@EnableConfigurationProperties({EntityKafkaProperties.class})
@Log4j2
public class EntityKafkaConfigure extends DengKafkaConfigure{
    @Resource
    protected EntityKafkaProperties entityKafkaProperties;
    @PostConstruct
    protected void setProperties() {
        super.properties = entityKafkaProperties;
    }
    @Bean(value = "entityKafkaListenerContainerFactory")
    public KafkaListenerContainerFactory<ConcurrentMessageListenerContainer<String, KafkaEntity>> entityKafkaListenerContainerFactory() {
        log.info("Deng|初始化entityKafkaListenerContainerFactory");
        return kafkaListenerContainerFactory();
    }

    @Bean(value = "entityKafkaTemplate")
    public KafkaTemplate<?, ?> entityKafkaTemplate() {
        log.info("Deng|初始化entityKafkaTemplate");
        return super.kafkaTemplate();
    }

}
