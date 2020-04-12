package com.iammybest.springboot.kafka.controller;

import com.iammybest.springboot.vo.KafkaEntity;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @DESCRIBE:
 * @TIME: 2020/3/27 16:51
 * @AUTHOR: qinghai.deng
 **/
@RestController
@RequestMapping("kafka/entity")
public class EntityKafkaController {
    @Resource
    KafkaTemplate<String,KafkaEntity> entityKafkaTemplate;
    /**
     * 发送实体
     * @return
     */
    @GetMapping("sendEntity")
    public String sendEntity(@RequestParam("name")String name,
                             @RequestParam("age")Integer age,
                             @RequestParam("sex")String sex,@RequestParam(value = "batch",required = false,defaultValue = "false")boolean batch,
                             @RequestParam(value = "batchSize",required = false,defaultValue = "1")int batchSize){
        if(batch){
            for (int i =0;i<batchSize;i++){
                entityKafkaTemplate.send(new ProducerRecord("deng-entity",System.currentTimeMillis()+"",new KafkaEntity(name+i,age,sex)));
            }
        }else{
            entityKafkaTemplate.send(new ProducerRecord("deng-entity",System.currentTimeMillis()+"",new KafkaEntity(name,age,sex)));
        }
        return "Send SUCCESS";
    }
}
