package com.iammybest.spring.mvc.mongo.log;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;

/**
 * Created by MrDeng on 2017/3/23.
 */
public class LoginLogMongo {
    MongoTemplate mongoTemplate;

    @Autowired
    public LoginLogMongo(MongoTemplate mongoTemplate){
        this.mongoTemplate = mongoTemplate;
//        mongoTemplate.setReadPreference();
//        mongoTemplate
    }
}
