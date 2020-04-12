package com.iammybest.mq;

import com.iammybest.mq.listener.UserUpdateListener;
import com.iammybest.mq.manager.MQConsumerManager;
import com.iammybest.mq.manager.MQServiceName;

/**
 * Created by MrDeng on 2017/2/23.
 */
public class ConsumerMain {
    public static void main(String[] args) throws Exception {
        MQConsumerManager.getInstance().startup();
        MQConsumerManager.getInstance().getHttpMQConsumer(MQServiceName.USER_UPDATE).listenMessage(new UserUpdateListener(),"*");
    }
}
