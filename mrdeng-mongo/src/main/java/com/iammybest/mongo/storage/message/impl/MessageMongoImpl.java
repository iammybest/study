package com.iammybest.mongo.storage.message.impl;

import com.alibaba.fastjson.JSON;
import com.iammybest.mongo.doc.Device;
import com.iammybest.mongo.doc.MessageEntity;
import com.iammybest.mongo.page.MongoPage;
import com.iammybest.mongo.storage.impl.BaseMongoImpl;
import com.iammybest.mongo.storage.message.MessageMongo;
import com.mongodb.client.FindIterable;
import com.mongodb.client.model.Updates;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by MrDeng on 2017/3/23.
 */
public class MessageMongoImpl extends BaseMongoImpl<Long> implements MessageMongo {
    public MessageMongoImpl(String dbName, String collName, String idProperties) {
        super(dbName, collName, idProperties);
    }

    public MessageMongoImpl() {
        this("deng", "group_message", "_id");
    }

    @Override
    public void add(MessageEntity messageEntity) {
        JSONObject json = messageEntity.toJSON();
        try {
            json.put("_id", messageEntity.getReceiver().getName() + "_" + messageEntity.getSerialNumber());
            System.out.println(json.toString());
            add(Document.parse(json.toString()));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteByToAndSn(String to, long sn) {
        delete(new Document("to", to).append("sn", sn));
    }

    @Override
    public void updateMessage(String to, long sn,ArrayList<Device> pulledDevices) {
//        getMongoCollection().updateOne();
        Document queryDoc = new Document().append("_id", to+"_"+sn);

        ArrayList<Document> deviceDocs= new ArrayList<>();
        for(Device device:pulledDevices){
            deviceDocs.add(Document.parse(device.toString()));
        }
        Bson update= Updates.addToSet("pulledDevices", deviceDocs);
        System.out.println(JSON.toJSONString(update));
//        System.out.println(JSON.toJSONString(deviceDocs));
        getMongoCollection().updateOne(queryDoc,update);
    }

    @Override
    public MessageEntity findOneByToAndSn(String to, long sn) {
        Document document = findOne(new Document("to.name", to).append("sn", sn));
        // TODO: 2017/3/23 document组装 MessageEntity
//        MessageEntity messageEntity
        System.out.println(JSON.toJSONString(document));


        return null;
    }

    @Override
    public List<MessageEntity> findByTo(String to, MongoPage mongoPage) {
        FindIterable<Document> documents = getMongoCollection().find(new Document("to.name", to))
                .sort(new Document(mongoPage.getOrderBy(), mongoPage.getOrder()))
                .skip(mongoPage.getSkip()).limit(mongoPage.getLimit());
        for (Document doc : documents) {
            System.out.println(JSON.toJSONString(doc));
        }
        // TODO: 2017/3/23 document组装 MessageEntity
//        MessageEntity messageEntity
//        return JSON.parseObject(document.toJson(),MessageEntity.class);
        return null;
    }

    @Override
    public List<MessageEntity> findByFromAndTo(String from, String to, MongoPage mongoPage) {
        FindIterable<Document> documents = getMongoCollection().find(new Document("from.name", from).append("to.name", to))
                .sort(new Document(mongoPage.getOrderBy(), mongoPage.getOrder()))
                .skip(mongoPage.getSkip()).limit(mongoPage.getLimit());
        for (Document doc : documents) {
            System.out.println(JSON.toJSONString(doc));
        }
        return null;
    }

    @Override
    public List<MessageEntity> findByGroup(String group, MongoPage mongoPage) {
        FindIterable<Document> documents = getMongoCollection().find(new Document("group.name", group))
                .sort(new Document(mongoPage.getOrderBy(), mongoPage.getOrder()))
                .skip(mongoPage.getSkip()).limit(mongoPage.getLimit());

        for (Document doc : documents) {
            System.out.println(JSON.toJSONString(doc));
        }
        return null;
    }

    @Override
    public List<MessageEntity> findByGroupAndIsPulledAndCreateTimeBetween(String group, boolean isPulled, long start, long end, MongoPage mongoPage) {
        FindIterable<Document> documents = getMongoCollection().find(new Document("group.name", group))
                .sort(new Document(mongoPage.getOrderBy(), mongoPage.getOrder()))
                .skip(mongoPage.getSkip()).limit(mongoPage.getLimit());

        for (Document doc : documents) {
            System.out.println(JSON.toJSONString(doc));
        }
        return null;
    }

    @Override
    public List<MessageEntity> findByToAndGroupAndIsPulledAndCreateTimeBetween(String to, String group, boolean isPulled, long start, long end, MongoPage mongoPage) throws JSONException {
        Document timeArea = new Document();
        timeArea.put("$gte", start);
        timeArea.put("$lte", end);
        Document queryDoc = new Document()
                .append("group.name", group).append("to.name", to)
                .append("time.timestamp", timeArea);
        FindIterable<Document> documents = getMongoCollection().find(queryDoc)
                .sort(new Document(mongoPage.getOrderBy(), mongoPage.getOrder()))
                .skip(mongoPage.getSkip()).limit(mongoPage.getLimit());
        for (Document doc : documents) {
            System.out.println(JSON.toJSONString(doc));
        }
        return null;
    }

    @Override
    public List<MessageEntity> findByToAndIsPulledAndCreateTimeBetween(String to, boolean isPulled, long start, long end, MongoPage mongoPage) {
        return null;
    }

    @Override
    public List<MessageEntity> findByFromAndToAndGroupAndIsPulledAndCreateTimeBetween(String from, String to, String group, boolean isPulled, long start, long end, MongoPage mongoPage) {
        FindIterable<Document> documents = getMongoCollection().find(new Document("group.name", group))
                .sort(new Document(mongoPage.getOrderBy(), mongoPage.getOrder()))
                .skip(mongoPage.getSkip()).limit(mongoPage.getLimit());

        for (Document doc : documents) {
            System.out.println(JSON.toJSONString(doc));
        }
        return null;
    }

}
