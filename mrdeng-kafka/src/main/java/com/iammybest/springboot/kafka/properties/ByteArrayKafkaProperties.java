package com.iammybest.springboot.kafka.properties;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @DESCRIBE:
 * @TIME: 2020/3/28 11:54
 * @AUTHOR: qinghai.deng
 **/
@ConfigurationProperties(
        prefix = "kafka.byte-array"
)
@Slf4j
public class ByteArrayKafkaProperties extends DengKafkaProperties {
    public ByteArrayKafkaProperties() {
        log.info("Deng|初始化获取配置项|kafka.byte-array");
    }
}
