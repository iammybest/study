package com.iammybest.springboot.kafka.serialization;

import com.alibaba.fastjson.JSON;
import com.iammybest.springboot.vo.KafkaEntity;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.common.serialization.Deserializer;

/**
 * @DESCRIBE:
 * @TIME: 2020/3/26 14:34
 * @AUTHOR: qinghai.deng
 **/
@Slf4j
public class EntityDeserializer implements Deserializer<KafkaEntity> {
    @Override
    public KafkaEntity deserialize(String s, byte[] bytes) {
        log.info("Deng|自定义反序列化组件EntityDeserializer|deserializer");
        return JSON.parseObject(bytes,KafkaEntity.class);
    }

    @Override
    public void close() {
        log.info("Deng|自定义反序列化组件EntityDeserializer|close");
    }
}
