package com.iammybest.mq.manager;

import com.aliyun.openservices.ons.api.PropertyKeyConst;
import com.aliyun.openservices.shade.com.alibaba.rocketmq.shade.io.netty.util.internal.StringUtil;
import com.iammybest.mq.log.LogManager;
import com.iammybest.mq.log.Logger;
import com.iammybest.mq.producer.MQProducer;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by MrDeng on 2017/2/17.
 */
public class MQProducerManager {

    private static final ConcurrentHashMap<String, MQProducer> producerMap = new ConcurrentHashMap<>();

    private static MQProducerManager me = new MQProducerManager();

    private static String TOPIC = "topic";

    private MQProducerManager() {
    }

    public static MQProducerManager getInstance() {
        return me;
    }

    public MQProducer getHttpMQProducer(MQServiceName mqType) {
        return producerMap.get(mqType.getType());
    }

    public void startup() {
        Properties mqProperties = new Properties();
        Logger.i(this.getClass(), " startup " );
        try {
            mqProperties.load(MQProducerManager.class.getClassLoader().getResourceAsStream("mq.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Logger.i(this.getClass(), "success " );
        for (MQServiceName type : MQServiceName.values()) {
            if (StringUtil.isNullOrEmpty(mqProperties.getProperty("mq." + type.getType() + ".topic") ) ||
                    StringUtil.isNullOrEmpty(mqProperties.getProperty("mq." + type.getType() + "." + PropertyKeyConst.ProducerId) ) ||
                    StringUtil.isNullOrEmpty(mqProperties.getProperty("mq." + type.getType() + "." + PropertyKeyConst.AccessKey) ) ||
                    StringUtil.isNullOrEmpty(mqProperties.getProperty("mq." + type.getType() + "." + PropertyKeyConst.SecretKey) ) ||
                    StringUtil.isNullOrEmpty(mqProperties.getProperty("mq." + type.getType() + "." + PropertyKeyConst.ONSAddr) )) {
                Logger.i(this.getClass(), "there is some problem in  properties of " + type);
                continue;
            }
            Properties config = new Properties();
            config.setProperty(TOPIC, mqProperties.getProperty("mq." + type.getType() + "." + TOPIC) );
            config.setProperty(PropertyKeyConst.ProducerId, mqProperties.getProperty("mq." + type.getType() + "." + PropertyKeyConst.ProducerId) );
            config.setProperty(PropertyKeyConst.AccessKey, mqProperties.getProperty("mq." + type.getType() + "." + PropertyKeyConst.AccessKey) );
            config.setProperty(PropertyKeyConst.SecretKey, mqProperties.getProperty("mq." + type.getType() + "." + PropertyKeyConst.SecretKey) );
            config.setProperty(PropertyKeyConst.ONSAddr, mqProperties.getProperty("mq." + type.getType() + "." + PropertyKeyConst.ONSAddr) );
            synchronized (this) {
                if (producerMap.get(type.getType()) == null) {
                    Logger.i(this.getClass(), "create consumer " + type);
                    producerMap.put(type.getType(), new MQProducer(config));
                }
            }
        }
    }
}
