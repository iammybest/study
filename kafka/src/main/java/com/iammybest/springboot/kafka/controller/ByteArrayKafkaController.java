package com.iammybest.springboot.kafka.controller;

/**
 * @DESCRIBE:
 * @TIME: 2020/3/28 12:05
 * @AUTHOR: qinghai.deng
 **/

import lombok.extern.slf4j.Slf4j;
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
    KafkaTemplate<byte[],byte[]> autoKafkaTemplate;
    @GetMapping("sendByteArray")
    public String sendEntity(@RequestParam("value")String value,
                             @RequestParam(value = "batch",required = false,defaultValue = "false")boolean batch,
                             @RequestParam(value = "batchSize",required = false,defaultValue = "1")int batchSize){
        if(batch){
            for (int i =0;i<batchSize;i++){
                autoKafkaTemplate.send("deng-byte-array",value.getBytes()).addCallback(
                        new SuccessCallback() {
                            @Override
                            public void onSuccess(Object o) {
                                log.info("Deng|SUCCESS|send message {}",value);
                            }
                        }, new FailureCallback() {
                            @Override
                            public void onFailure(Throwable throwable) {
                                log.error("Deng|FAILED|send message {}",value);
                            }
                        });
            }
        }else{
            autoKafkaTemplate.send("deng-byte-array",value.getBytes());
        }
        return "Send SUCCESS";
    }
}
