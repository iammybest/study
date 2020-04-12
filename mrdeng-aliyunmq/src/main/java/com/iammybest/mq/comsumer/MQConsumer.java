package com.iammybest.mq.comsumer;

import com.aliyun.openservices.ons.api.Consumer;
import com.aliyun.openservices.ons.api.MessageListener;
import com.aliyun.openservices.ons.api.ONSFactory;
import com.aliyun.openservices.ons.api.order.MessageOrderListener;
import com.aliyun.openservices.ons.api.order.OrderConsumer;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * Created by MrDeng on 2017/2/17.
 */
public class MQConsumer {
    private static Map<String, MQConsumer> instanceMap = new HashMap<String, MQConsumer>();
    private Consumer consumer;
    private Properties config;
    OrderConsumer oderConsumer;
    private MQConsumer() {
    }
    public MQConsumer(Properties config) throws NullPointerException {
        if (config == null) {
            throw new NullPointerException("MQ config cannot be null");
        }
        this.config = config;
        this.consumer = ONSFactory.createConsumer(config);
        oderConsumer = ONSFactory.createOrderedConsumer(config);
    }

    public void listenMessage(MessageListener listener,String tag) throws Exception {
        if (consumer.isStarted())
            throw new Exception("Cunsumer is starting");
        consumer.subscribe(this.config.getProperty("topic"), tag, listener);
        consumer.start();
        System.out.println("MessageListener is listening");
    }

    public void listenMessage(MessageOrderListener listener,String tag) throws Exception {
        if (oderConsumer.isStarted())
            throw new Exception("Cunsumer is starting");
        oderConsumer.subscribe(this.config.getProperty("topic"), tag, listener);
        oderConsumer.start();
        System.out.println("MessageOrderListener is listening");
    }
}
