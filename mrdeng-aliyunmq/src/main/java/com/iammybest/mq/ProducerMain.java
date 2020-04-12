package com.iammybest.mq;

import com.iammybest.mq.log.Logger;
import com.iammybest.mq.manager.MQProducerManager;
import com.iammybest.mq.manager.MQServiceName;
import com.iammybest.mq.message.UserMqMessage;
import com.iammybest.mq.producer.MQProducer;

/**
 * Created by MrDeng on 2017/2/23.
 */
public class ProducerMain {
    public static void main(String[] args) throws InterruptedException {
        MQProducerManager.getInstance().startup();

        Logger.i(ProducerMain.class, "send mq message " );
        for(int i =0;i<6;i++){
            Logger.i(ProducerMain.class, "send mq message " +i);
            UserMqMessage userMsg = new UserMqMessage("mrdeng");
            MQProducerManager.getInstance().getHttpMQProducer(MQServiceName.USER_UPDATE).send(userMsg,
                    "TagA",System.currentTimeMillis()+"");
            Logger.i(ProducerMain.class, "send mq message " + userMsg.toString());
            Thread.sleep(3000L);
        }
    }
}
