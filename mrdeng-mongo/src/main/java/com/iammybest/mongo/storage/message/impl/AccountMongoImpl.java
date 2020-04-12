package com.iammybest.mongo.storage.message.impl;

import com.iammybest.mongo.storage.impl.BaseMongoImpl;
import com.iammybest.mongo.storage.message.AccountMongo;
import org.bson.types.ObjectId;

/**
 * Created by MrDeng on 2017/3/30.
 */
public class AccountMongoImpl extends BaseMongoImpl<ObjectId> implements AccountMongo {
    public AccountMongoImpl(String dbName, String collName, String idProperties) {
        super(dbName, collName, idProperties);
    }

    public AccountMongoImpl() {
        super("mrdeng", "account", "_id");
    }
}
