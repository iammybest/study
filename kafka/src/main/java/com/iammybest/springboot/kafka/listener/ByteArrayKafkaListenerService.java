package com.iammybest.springboot.kafka.listener;

import com.alibaba.fastjson.JSON;
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
public class ByteArrayKafkaListenerService {

    /*** 对应 application-byte-array配置**/
    @KafkaListener(id = "deng-byte-array-batch-0",groupId = "deng-group-byte-array-batch",
            containerFactory = "entityKafkaListenerContainerFactory",
            topicPartitions = {@TopicPartition(topic = "deng-byte-array", partitions = {"0","1"})}
            )
    private void kafkaListenerByteArrayRecordList(List<ConsumerRecord<byte[], byte[]>> dataList){
        log.info("Deng|entity-batch List| {}", JSON.toJSONString(dataList));
        for (ConsumerRecord<byte[], byte[]> record:dataList){
            log.info("Deng|entity-batch FOR| {}",record.toString());
        }
    }

    @KafkaListener(id = "deng-byte-array-batch-1",groupId = "deng-group-byte-array-list",
            containerFactory = "entityKafkaListenerContainerFactory",
            topicPartitions = {@TopicPartition(topic = "deng-byte-array", partitions = {"0","1"})}
    )
    private void kafkaListenerByteArrayRecord(ConsumerRecord<byte[], byte[]> byteArray){
        log.info("Deng|entity-list List| {}", JSON.toJSONString(byteArray));
    }
    @KafkaListener(id = "deng-byte-array-2",groupId = "deng-group-byte-array",
            containerFactory = "entityKafkaListenerContainerFactory",
            topicPartitions = {@TopicPartition(topic = "deng-byte-array", partitions = {"0","1"})})
    private void kafkaListenerByteArray0(byte[] byteArray){
            log.info("Deng|entity|: {}",byteArray.toString());
    }
}
