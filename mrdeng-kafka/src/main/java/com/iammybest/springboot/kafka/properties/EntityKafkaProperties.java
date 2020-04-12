package com.iammybest.springboot.kafka.properties;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @DESCRIBE: 自定义机制Kafka配置
 * @TIME: 2020/3/26 10:58
 * @AUTHOR: qinghai.deng
 **/
@ConfigurationProperties(
        prefix = "kafka.entity"
)
@Slf4j
public class EntityKafkaProperties extends DengKafkaProperties {
    public EntityKafkaProperties() {
        log.info("Deng|初始化获取配置项|kafka.entity");
    }
}
