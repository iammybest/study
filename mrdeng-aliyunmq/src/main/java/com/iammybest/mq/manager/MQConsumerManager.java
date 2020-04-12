package com.iammybest.mq.manager;

import com.aliyun.openservices.ons.api.PropertyKeyConst;
import com.aliyun.openservices.shade.com.alibaba.rocketmq.shade.io.netty.util.internal.StringUtil;
import com.iammybest.mq.comsumer.MQConsumer;

import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by MrDeng on 2017/2/17.
 */
public class MQConsumerManager {

    ConcurrentHashMap<String, MQConsumer> consumerMap = new ConcurrentHashMap<>();
    private static MQConsumerManager me = new MQConsumerManager();
    private MQConsumerManager() {
    }

    public static MQConsumerManager getInstance() {
        return me;
    }

    public MQConsumer getHttpMQConsumer(MQServiceName mqType) {
        return consumerMap.get(mqType.getType());
    }

    public void startup() {
        Properties mqProperties = new Properties();
        try {
            mqProperties.load(MQProducerManager.class.getClassLoader().getResourceAsStream("mq.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        for (MQServiceName type : MQServiceName.values()) {
            if (StringUtil.isNullOrEmpty(mqProperties.getProperty("mq." + type.getType() + ".topic" ))||
                    StringUtil.isNullOrEmpty(mqProperties.getProperty("mq." + type.getType() + "." + PropertyKeyConst.ConsumerId) ) ||
                    StringUtil.isNullOrEmpty(mqProperties.getProperty("mq." + type.getType() + "." + PropertyKeyConst.AccessKey) ) ||
                    StringUtil.isNullOrEmpty(mqProperties.getProperty("mq." + type.getType() + "." + PropertyKeyConst.SecretKey) )) {
                continue;
            }
            Properties config = new Properties();
            config.setProperty("topic", mqProperties.getProperty("mq." + type.getType() + ".topic") );
            config.setProperty(PropertyKeyConst.ConsumerId, mqProperties.getProperty("mq." + type.getType() + "." + PropertyKeyConst.ConsumerId) );
            config.setProperty(PropertyKeyConst.AccessKey, mqProperties.getProperty("mq." + type.getType() + "." + PropertyKeyConst.AccessKey) );
            config.setProperty(PropertyKeyConst.SecretKey, mqProperties.getProperty("mq." + type.getType() + "." + PropertyKeyConst.SecretKey) );
            synchronized (this) {
                if (consumerMap.get(type.getType()) == null) {
                    consumerMap.put(type.getType(), new MQConsumer(config));
                }
            }
        }
    }
}
