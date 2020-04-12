package com.iammybest.spring.mvc.mongo;

import com.alibaba.fastjson.JSON;
import com.iammybest.spring.mvc.doc.AccountDoc;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.net.MalformedURLException;

/**
 * Created by MrDeng on 2017/3/22.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
//        "classpath:mrdeng-spring-mvc-servlet.xml",
        "classpath:spring-mongo.xml"
})
public class MongoAddTest {
    @Autowired
    MongoTemplate mongoTemplate;
    @Test
    public void test() throws MalformedURLException {
        AccountDoc accountDoc = new AccountDoc();
        accountDoc.setAccount("mrdeng");
        accountDoc.setPassword("123456");
        mongoTemplate.save(accountDoc);
//        mongoTemplate.setReadPreference();
        System.out.println("+++++++++++++"+JSON.toJSONString(accountDoc));
//        Query query = new Query(Criteria.where("coll_name").is("mrdeng"));
//        Update update = new Update();
//        update.inc("seq_id", 1);
//        FindAndModifyOptions options = new FindAndModifyOptions();
//        options.upsert(true);
//        options.returnNew(true);
//        SequenceId id=mongoTemplate.findAndModify(query, update, options, SequenceId.class);
//        System.out.println("+++++++++++++++++++++++++++++++++"+id.getSeqId());
    }
}
