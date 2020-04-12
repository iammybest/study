package com.iammybest.mq.producer;

import com.aliyun.openservices.ons.api.*;
import com.aliyun.openservices.ons.api.order.OrderProducer;
import com.iammybest.mq.message.MqMessage;

import java.util.Properties;

/**
 * Created by MrDeng on 2017/2/17.
 */
public class MQProducer {
    private static final String NEWLINE = "\n";

    /**
     * 普通消息发布者
     */
    Producer producer;
    /**
     * 顺序消息发布者
     */
    OrderProducer orderProducer;

    Properties config;

    private MQProducer() {
    }

    /**
     * 创建并保存HttpMQManager
     *
     * @return
     */
    public MQProducer(Properties config) throws NullPointerException {
        if (config == null) {
            throw new NullPointerException("MQ config cannot be null");
        }
        this.config = config;
        this.producer = ONSFactory.createProducer(config);
        this.orderProducer = ONSFactory.createOrderProducer(config);
        producer.start();
        orderProducer.start();
        //在发送消息前，必须调用start方法来启动Producer，只需调用一次即可。
    }

    /**
     * 顺序发送消息
     *
     * @param msg 消息内容
     * @param tag 标签
     * @param key 关键字 用于搜索
     * @return
     */
    public SendResult sendOrder(MqMessage msg, String tag, String key) {
        Message sendMessage = new Message(config.getProperty("topic"), tag, msg.toString().getBytes());
        sendMessage.setKey(key);
        return orderProducer.send(sendMessage, key);
    }

    /**
     * 发送普通消息
     * @param msg
     * @return
     */
    public SendResult send(MqMessage msg, String tag,String key) {
        Message sendMessage = new Message(config.getProperty("topic"), tag, msg.toString().getBytes());
        sendMessage.setKey(key);
        return producer.send(sendMessage);
    }

    /**
     * 异步发送普通消息
     * @param msg
     * @param tag
     * @param key
     * @param callback
     * @return
     */
    public boolean sendAsync(MqMessage msg, String tag, String key, SendCallback callback) {
        Message sendMessage = new Message(config.getProperty("topic"), tag, msg.toString().getBytes());
        sendMessage.setKey(key);
        producer.sendAsync(sendMessage,callback);
        return true;
    }

    /**
     * 单向发送普通消息
     * @param msg
     * @param tag
     * @param key
     * @return
     */
    public boolean sendOneWay(MqMessage msg, String tag,String key) {
        Message sendMessage = new Message(config.getProperty("topic"), tag, msg.toString().getBytes());
        sendMessage.setKey(key);
        producer.sendOneway(sendMessage);//单向发送消息 无返回值
        return true;
    }
}
