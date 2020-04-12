package com.iammybest.mongo;

import com.iammybest.common.log.Logger;
import com.mongodb.MongoClient;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by MrDeng on 2017/3/23.
 */
public class MongoFactory {

    MongoClient mongoClient = null;
    private static MongoFactory me = new MongoFactory();

    private MongoFactory() {

    }

    public static MongoFactory getInstance() {
        return me;
    }

    public void startup(String host) {
        if (null != mongoClient) {
            Logger.i(this.getClass(), "Mongo client has been initialed");
            return;
        }
        if (null == host || host.isEmpty()) {
            Logger.i(this.getClass(), "host cannot be null or empty");
            return;
        }

        mongoClient = new MongoClient(host);
    }

    public void startup(List<String> hosts) {
        if (null != mongoClient) {
            Logger.i(this.getClass(), "Mongo client has been initialed");
        }
        if (null == hosts || hosts.isEmpty()) {
            Logger.i(this.getClass(), "host cannot be null or empty");
            return;
        }
        List<ServerAddress> addresses = new ArrayList<>();
        for (String host :hosts){
            addresses.add(new ServerAddress(host));
        }
        mongoClient = new MongoClient(addresses);
    }

    public MongoDatabase getDB(String dbName){
        return mongoClient.getDatabase(dbName);
    }
}
