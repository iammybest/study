package com.iammybest.springboot.kafka.controller;

/**
 * @DESCRIBE:
 * @TIME: 2020/3/28 12:05
 * @AUTHOR: qinghai.deng
 **/

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.util.concurrent.FailureCallback;
import org.springframework.util.concurrent.SuccessCallback;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("kafka/byte-array")
@Slf4j
public class ByteArrayKafkaController {

    @Resource
    KafkaTemplate<byte[],byte[]> byteArrayKafkaTemplate;
    @GetMapping("sendByteArray")
    public String sendEntity(@RequestParam("key")String key,
                             @RequestParam("value")String value,
                             @RequestParam(value = "batch",required = false,defaultValue = "false")boolean batch,
                             @RequestParam(value = "batchSize",required = false,defaultValue = "1")int batchSize){
        if(batch){
            for (int i =0;i<batchSize;i++){
                byteArrayKafkaTemplate.send(new ProducerRecord<byte[], byte[]>("deng-byte-array",(key+i).getBytes(),(value+i).getBytes())).addCallback(
                        new SuccessCallback() {
                            @Override
                            public void onSuccess(Object o) {
                                log.info("Deng|SUCCESS|send byte-array message {}",value);
                            }
                        }, new FailureCallback() {
                            @Override
                            public void onFailure(Throwable throwable) {
                                log.error("Deng|FAILED|send byte-array message {}",value);
                            }
                        });
            }
        }else{
            byteArrayKafkaTemplate.send(new ProducerRecord<byte[], byte[]>("deng-byte-array",key.getBytes(),value.getBytes()));
        }
        return "Send SUCCESS";
    }
}
