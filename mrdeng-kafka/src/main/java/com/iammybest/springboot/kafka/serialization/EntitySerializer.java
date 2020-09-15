package com.iammybest.springboot.kafka.serialization;

import com.alibaba.fastjson.JSON;
import com.iammybest.springboot.vo.KafkaEntity;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.common.serialization.Serializer;

import java.util.Map;

/**
 * @DESCRIBE:
 * @TIME: 2020/3/26 11:14log.info("自定义的序列化组件--close");
 * @AUTHOR: qinghai.deng
 **/
@Slf4j
public class EntitySerializer implements Serializer<KafkaEntity> {
    public void configure(Map<String, ?> configs, boolean isKey) {
        log.info("Deng|自定义序列化组件EntitySerializer|configure");
    }

    public byte[] serialize(String s, KafkaEntity kafkaEntity) {
        log.info("Deng|自定义序列化组件EntitySerializer|serialize");
        return JSON.toJSONBytes(kafkaEntity);
    }

    public void close() {
        log.info("Deng|自定义序列化组件|close");
    }
}
