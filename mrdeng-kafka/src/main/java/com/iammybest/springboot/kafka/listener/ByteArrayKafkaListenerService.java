package com.iammybest.springboot.kafka.listener;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
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
            containerFactory = "byteArrayKafkaListenerContainerFactory",
            topicPartitions = {@TopicPartition(topic = "deng-byte-array", partitions = {"0","1"})}
            )
    private void kafkaListenerByteArrayRecordList(ConsumerRecords<byte[], byte[]> dataList){
        log.info("Deng|byteArray-batch List| {}", JSON.toJSONString(dataList));
        for (ConsumerRecord<byte[], byte[]> record:dataList){
            log.info("Deng|byteArray-batch FOR|key:{} value:{}",new String(record.key()),new String(record.value()));
        }
    }

//    @KafkaListener(id = "deng-byte-array-batch-1",groupId = "deng-group-byte-array-list",
//            containerFactory = "byteArrayKafkaListenerContainerFactory",
//            topicPartitions = {@TopicPartition(topic = "deng-byte-array", partitions = {"0","1"})}
//    )
//    private void kafkaListenerByteArrayRecord(ConsumerRecords<byte[], byte[]> record){
//        log.info("Deng|byteArray-list List| {}", JSON.toJSONString(record));
//    }
    @KafkaListener(id = "deng-byte-array-2",groupId = "deng-group-byte-array",
            containerFactory = "byteArrayKafkaListenerContainerFactory",
            topicPartitions = {@TopicPartition(topic = "deng-byte-array", partitions = {"0","1"})})
    private void kafkaListenerByteArray0(byte[] byteArray){
            log.info("Deng|byte-array|: {}",new String(byteArray));
    }
}
