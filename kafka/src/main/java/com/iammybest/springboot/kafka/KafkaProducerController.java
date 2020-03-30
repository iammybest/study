//package com.iammybest.springboot.kafka.kafka;
//
//import com.alibaba.fastjson.JSON;
//import com.iammybest.springboot.vo.KafkaEntity;
//import org.apache.kafka.clients.producer.ProducerRecord;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.kafka.core.KafkaTemplate;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//
//import javax.annotation.Resource;
//
///**
// * @DESCRIBE:
// * @TIME: 2020/1/17 9:51
// * @AUTHOR: qinghai.deng
// **/
////@RestController
////@RequestMapping("kafka")
//public class KafkaProducerController {
//    Logger logger = LoggerFactory.getLogger(KafkaProducerController.class);
//    @Resource
//    KafkaTemplate kafkaTemplate;
//
//    @Resource
//    KafkaTemplate<String,KafkaEntity> recordKafkaTemplate;
//
//    @Resource
//    KafkaTemplate<byte[], byte[]> byteKafkaTemplate;
//
//
//    /**
//     * 发送字符串类型
//     * @param msg
//     * @return
//     */
//    @GetMapping("sendStr")
//    public String sendStr(@RequestParam("msg")String msg,
//                          @RequestParam(value = "batch",required = false,defaultValue = "false")boolean batch,
//                          @RequestParam(value = "batchSize",required = false,defaultValue = "1")int batchSize){
//        if(batch){
//            for (int i =0;i<batchSize;i++){
//                kafkaTemplate.send("deng-batch",msg+i);
//            }
//        }else{
//            kafkaTemplate.send("deng-string",msg);
//        }
//        return msg;
//    }
//
//    /**
//     * 发送key value键值对
//     * @param key
//     * @param value
//     * @return
//     */
//    @GetMapping("sendRecordStr")
//    public String sendRecordStr(@RequestParam("key")String key,@RequestParam("value")String value,
//                             @RequestParam(value = "batch",required = false,defaultValue = "false")boolean batch,
//                             @RequestParam(value = "batchSize",required = false,defaultValue = "1")int batchSize){
//        if(batch){
//            for (int i =0;i<batchSize;i++){
//                kafkaTemplate.send("deng-batch",(int)(System.currentTimeMillis()%2),(key+i).getBytes(),(value+i).getBytes());
//            }
//        }else{
//            kafkaTemplate.send("deng-record",(int)(System.currentTimeMillis()%2),key.getBytes(),value.getBytes());
//        }
//
//        return key+" "+value;
//    }
//    /**
//     * 发送key value键值对
//     * @param key
//     * @param value
//     * @return
//     */
//    @GetMapping("sendRecordByte")
//    public String sendRecordByte(@RequestParam("key")String key,@RequestParam("value")String value,
//                             @RequestParam(value = "batch",required = false,defaultValue = "false")boolean batch,
//                             @RequestParam(value = "batchSize",required = false,defaultValue = "1")int batchSize){
//        if(batch){
//            for (int i =0;i<batchSize;i++){
//                kafkaTemplate.send("deng-batch",(int)(System.currentTimeMillis()%2),key+i,value+i);
//            }
//        }else{
//            kafkaTemplate.send("deng-record",(int)(System.currentTimeMillis()%2),key,value);
//        }
//
//        return key+" "+value;
//    }
//    /**
//     * 发送实体
//     * @return
//     */
//    @GetMapping("sendEntityByte")
//    public String sendEntityByte(@RequestParam("name")String name,
//                             @RequestParam("age")Integer age,
//                             @RequestParam("sex")String sex,@RequestParam(value = "batch",required = false,defaultValue = "false")boolean batch,
//                             @RequestParam(value = "batchSize",required = false,defaultValue = "1")int batchSize){
//        if(batch){
//            for (int i =0;i<batchSize;i++){
//                byteKafkaTemplate.send(new ProducerRecord("deng-entity-batch",(System.currentTimeMillis()+"").getBytes(), JSON.toJSONBytes(new KafkaEntity(name+i,age,sex))));
//            }
//        }else{
//            byteKafkaTemplate.send(new ProducerRecord("deng-entity-batch",(System.currentTimeMillis()+"").getBytes(), JSON.toJSONBytes(new KafkaEntity(name,age,sex))));
//        }
//        return "Send SUCCESS";
//    }
//
//    /**
//     * 发送实体
//     * @return
//     */
//    @GetMapping("sendEntity")
//    public String sendEntity(@RequestParam("name")String name,
//                             @RequestParam("age")Integer age,
//                             @RequestParam("sex")String sex,@RequestParam(value = "batch",required = false,defaultValue = "false")boolean batch,
//                             @RequestParam(value = "batchSize",required = false,defaultValue = "1")int batchSize){
//        if(batch){
//            for (int i =0;i<batchSize;i++){
//                recordKafkaTemplate.send(new ProducerRecord("deng-entity",System.currentTimeMillis()+"",new KafkaEntity(name+i,age,sex)));
//            }
//        }else{
//            recordKafkaTemplate.send(new ProducerRecord("deng-entity",System.currentTimeMillis()+"",new KafkaEntity(name,age,sex)));
//        }
//        return "Send SUCCESS";
//    }
//}
