package com.iammybest.constants;

import com.iammybest.mongo.MongoFactory;
import com.iammybest.mongo.doc.Device;
import com.iammybest.mongo.doc.MessageEntity;
import com.iammybest.mongo.doc.Receiver;
import com.iammybest.mongo.doc.Sender;
import com.iammybest.mongo.page.MongoPage;
import com.iammybest.mongo.storage.message.MessageMongo;
import com.iammybest.mongo.storage.message.impl.MessageMongoImpl;
import junit.framework.TestCase;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Unit test for simple App.
 */


public class MongoTest extends TestCase {

    public static List<String> receivers = new ArrayList<>();

    public static List<String> groups = new ArrayList<>();

    @Before
    public void init() {
    }

    @Test
    public void test() throws MalformedURLException, JSONException {
        System.out.println("****************Before");
        receivers.add("MrDeng");
        receivers.add("DengQingHai");
        receivers.add("XiaoWang");
        receivers.add("XiaoLi");
        receivers.add("张三");
        receivers.add("李四");
        receivers.add("王武");
        receivers.add("Jack");
        receivers.add("Tom");
        receivers.add("Alia");
        receivers.add("Ella");
        receivers.add("JoJo");
        receivers.add("Jay");
        receivers.add("Fad Boy");


        groups.add("Chinese");
        groups.add("American");
        groups.add("England");

        MongoFactory.getInstance().startup("59.110.45.40:27100");
        MessageMongo messageMongo = new MessageMongoImpl();

        //初始化点数据
        //发送消息给receivers中其他人
//        for (int i = 0; i < receivers.size(); i++) {
//            MessageEntity message = new MessageEntity();
//            message.setSender(new Sender(receivers.get(i)));
//            message.setSerialNumber(System.currentTimeMillis());
//            for (int k = 0; k < receivers.size(); k++) {
//                if (k == i)
//                    continue;
//                message.setTimestamp(System.currentTimeMillis());
//
//                message.setReceiver(new Receiver(receivers.get(k)));
//                messageMongo.add(message);
//            }
//        }
//        //发送消息给 群里 receivers中其他人
//        for (int i = 0; i < receivers.size(); i++) {
//            MessageEntity message = new MessageEntity();
//            message.setSerialNumber(System.currentTimeMillis());
//            JSONObject group = new JSONObject();
//            group.put("name", groups.get((int) (System.currentTimeMillis() % groups.size())));
//            message.setGroup(group);
//            message.setSender(new Sender(receivers.get(i)));
//            //总共发给7个人
//            for (int k = 0; k < receivers.size(); k++) {
//                //非本人
//                if (k == i)
//                    continue;
//                message.setTimestamp(System.currentTimeMillis());
//                message.setReceiver(new Receiver(receivers.get(k)));
//                messageMongo.add(message);
//            }
//        }


        ArrayList<Device> pullDevices = new ArrayList<Device>();
//        Device device = new Device();
        pullDevices.add(new Device());
        pullDevices.add(new Device());
        messageMongo.updateMessage("DengQingHai",1490583016072L,pullDevices);


//        messageMongo.findOneByToAndSn("DengQingHai", 123456L);
//        messageMongo.findByTo("DengQingHai", new MongoPage().pageNo(1).pageSize(5).orderBy("time.timestamp").order(MongoPage.ORDER.DESC));
//        messageMongo.findByFromAndTo("DengQingHai", "JoJo",new MongoPage().pageNo(1).pageSize(5).orderBy("time.timestamp").order(MongoPage.ORDER.DESC));



//        messageMongo.findByToAndGroupAndIsPulledAndCreateTimeBetween("DengQingHai", "Chinese",false,1490583055896L,1490583161829L,
//                new MongoPage().pageNo(2).pageSize(2).orderBy("time.timestamp").order(MongoPage.ORDER.DESC));
//        messageMongo.findByGroup("Chinese", new MongoPage().pageNo(1).pageSize(5).orderBy("time.timestamp").order(MongoPage.ORDER.DESC));
//        System.out.println(JSON.toJSONString(docs));
    }
}
