package com.iammybest.mongo.storage.impl;

import com.iammybest.mongo.MongoFactory;
import com.iammybest.mongo.storage.BaseMongo;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.result.DeleteResult;
import org.bson.Document;
import org.bson.conversions.Bson;

import java.util.List;

/**
 * Created by MrDeng on 2017/3/23.
 */
public abstract class BaseMongoImpl<ID> implements BaseMongo< ID> {

    private String idProperties;
    private MongoCollection mongoCollection;

    public MongoCollection getMongoCollection() {
        return mongoCollection;
    }

    public BaseMongoImpl(String dbName, String collName,String idProperties) {
        this.idProperties = idProperties;
        mongoCollection = MongoFactory.getInstance().getDB(dbName).getCollection(collName);
    }

    /**
     * MongoCollection 原生支持的findOne 已删除需要自行实现
     *
     * @return
     */
    @Override
    public Document findOne(ID id) {
        FindIterable<Document> docs = mongoCollection.find(new Document(idProperties, id));
        return docs == null ? null : docs.first();
    }

    @Override
    public Document findOne(Bson bson) {
        FindIterable<Document> docs = mongoCollection.find(bson);
        return docs == null ? null : docs.first();
    }

    @Override
    public void add(Bson document) {
        mongoCollection.insertOne(document);
    }

    @Override
    public void add(List<Bson> ts) {
        mongoCollection.insertMany(ts);
    }

    @Override
    public long delete(ID id) {
        DeleteResult result = mongoCollection.deleteOne(new Document(idProperties, id));
        return result == null ? 0 : result.getDeletedCount();
    }

    @Override
    public long delete(Bson bson) {
        DeleteResult result = mongoCollection.deleteMany(bson);
        return result == null ? 0 : result.getDeletedCount();
    }

    @Override
    public FindIterable<Document>find(Bson bson) {
        return mongoCollection.find(bson);
    }
}
