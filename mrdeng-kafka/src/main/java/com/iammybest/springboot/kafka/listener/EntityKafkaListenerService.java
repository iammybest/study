package com.iammybest.springboot.kafka.listener;

import com.alibaba.fastjson.JSON;
import com.iammybest.springboot.vo.KafkaEntity;
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
public class EntityKafkaListenerService {

    /*** 对应 application-entity配置**/
    @KafkaListener(id = "deng-entity-batch-0",groupId = "deng-group-entity-batch",
            containerFactory = "entityKafkaListenerContainerFactory",
            topicPartitions = {@TopicPartition(topic = "deng-entity", partitions = {"0","1"})}
            )
    private void kafkaListenerEntityByte(ConsumerRecords<String, KafkaEntity> consumerRecords){
        log.info("Deng|List<ConsumerRecord<String, KafkaEntity>>| {}", JSON.toJSONString(consumerRecords));
        for (ConsumerRecord<String, KafkaEntity> record:consumerRecords){
            log.info("Deng|entity-batch FOR| offset:{} key:{} value:{}",record.offset(),record.key(),record.value());
        }
    }

    @KafkaListener(id = "deng-entity-batch-1",groupId = "deng-group-entity-list",
            containerFactory = "entityKafkaListenerContainerFactory",
            topicPartitions = {@TopicPartition(topic = "deng-entity", partitions = {"0","1"})}
    )
    private void kafkaListenerEntityList(List<KafkaEntity> dataList){
        log.info("Deng|List<KafkaEntity> List| {}", JSON.toJSONString(dataList));
        for (KafkaEntity record:dataList){
            log.info("Deng|List<KafkaEntity> FOR| {}",record.toString());
        }
    }
    @KafkaListener(id = "deng-entity-2",groupId = "deng-group-entity",
            containerFactory = "entityKafkaListenerContainerFactory",
            topicPartitions = {@TopicPartition(topic = "deng-entity", partitions = {"0","1"})})
    private void kafkaListenerEntityRecord(ConsumerRecord<String, KafkaEntity> record){
        log.info("Deng|ConsumerRecord<String, KafkaEntity>|: offset:{} key:{} value:{}",record.offset(),record.key(),record.value());
    }
    @KafkaListener(id = "deng-entity-3",groupId = "deng-group-entity",
            containerFactory = "entityKafkaListenerContainerFactory",
            topicPartitions = {@TopicPartition(topic = "deng-entity", partitions = {"0","1"})})
    private void kafkaListenerEntity0(KafkaEntity entity){
            log.info("Deng|KafkaEntity|: {}",entity.toString());
    }
}
