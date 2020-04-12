package com.iammybest.mongo.storage.message;

import com.iammybest.mongo.doc.Device;
import com.iammybest.mongo.doc.MessageEntity;
import com.iammybest.mongo.page.MongoPage;
import com.iammybest.mongo.storage.BaseMongo;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by MrDeng on 2017/3/23.
 */
public interface MessageMongo extends BaseMongo<Long> {

    void add(MessageEntity messageEntity);

    void deleteByToAndSn(String to, long sn);

    void updateMessage(String to, long sn,ArrayList<Device> pulledDevices);

    MessageEntity findOneByToAndSn(String to, long sn);

    List<MessageEntity> findByTo(String to, MongoPage mongoPage);

    //
    List<MessageEntity> findByFromAndTo(String from, String to, MongoPage mongoPage);

    //
    List<MessageEntity> findByGroup(String group, MongoPage mongoPage);

    //
    List<MessageEntity> findByGroupAndIsPulledAndCreateTimeBetween(String group, boolean
            isPulled, long start, long end, MongoPage mongoPage);

    List<MessageEntity> findByToAndGroupAndIsPulledAndCreateTimeBetween(String to, String group, boolean isPulled, long start, long end, MongoPage mongoPage) throws JSONException;

    List<MessageEntity> findByToAndIsPulledAndCreateTimeBetween(String to, boolean isPulled, long start, long end, MongoPage mongoPage);

    List<MessageEntity> findByFromAndToAndGroupAndIsPulledAndCreateTimeBetween(String from, String to, String group, boolean isPulled, long start, long end, MongoPage mongoPage);
}
