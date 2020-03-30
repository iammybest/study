package com.iammybest.springboot.kafka.listener;

import com.alibaba.fastjson.JSON;
import com.iammybest.springboot.vo.KafkaEntity;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.TopicPartition;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @DESCRIBE:
 * @TIME: 2020/3/27 15:44
 * @AUTHOR: qinghai.deng
 **/
@Service
@EnableKafka
@Slf4j
public class AutoKafkaListenerService {
    /**
     * 配置批量获取后 结果
     * String,Value,String,Value,String,Value
     * String,Value,
     * ……………………
     * 随机 且每次处理为一个字符串
     * @param record
     */
    @KafkaListener(id = "deng-string-2",groupId = "deng-group-string",
            containerFactory = "autoKafkaListenerContainerFactory",
            topicPartitions = {@TopicPartition(topic = "deng-string", partitions = {"0","1"})})
    private void kafkaListenerString(String record){
        log.info("Deng|kafkaListenerString| {}",record);
    }

    /**
     * 批量获取  结果：["String,Value","String,Value","String,Value","String,Value","String,Value"]
     * 接收为数组形式
     * @param record
     */
    @KafkaListener(id = "deng-string-3",groupId = "deng-group-string-list",
            containerFactory = "autoKafkaListenerContainerFactory",
            topicPartitions = {@TopicPartition(topic = "deng-string", partitions = {"0","1"})})
    private void kafkaListenerStringList(List<String> record){
        log.info("Deng|kafkaListenerStringList| {}", JSON.toJSONString(record));
    }
}
