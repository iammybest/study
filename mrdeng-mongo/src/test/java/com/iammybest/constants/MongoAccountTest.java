package com.iammybest.constants;

import com.alibaba.fastjson.JSON;
import com.iammybest.mongo.MongoFactory;
import com.iammybest.mongo.doc.Device;
import com.iammybest.mongo.storage.message.MessageMongo;
import com.iammybest.mongo.storage.message.impl.AccountMongoImpl;
import com.iammybest.mongo.storage.message.impl.MessageMongoImpl;
import com.mongodb.BasicDBObject;
import com.mongodb.client.FindIterable;
import junit.framework.TestCase;
import org.bson.BsonDocument;
import org.bson.Document;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;
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


public class MongoAccountTest extends TestCase {

    public static List<String> receivers = new ArrayList<>();

    public static List<String> groups = new ArrayList<>();

    @Before
    public void init() {
    }

    @Test
    public void test() throws MalformedURLException, JSONException {
        MongoFactory.getInstance().startup("59.110.45.40:28100");
        MessageMongo messageMongo = new MessageMongoImpl();
        AccountMongoImpl accountMongo = new AccountMongoImpl();
        Document document=accountMongo.findOne(new ObjectId("58dcbb592a2e46d5a304ca59"));
        System.out.println(document.toJson());
        BasicDBObject toObj = new BasicDBObject("money",0);
        FindIterable<Document> docs = accountMongo.find(toObj);
        System.out.println(docs.first().toJson());
    }
}
