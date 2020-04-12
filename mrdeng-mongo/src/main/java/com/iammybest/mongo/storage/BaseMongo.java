package com.iammybest.mongo.storage;

import com.mongodb.client.FindIterable;
import org.bson.Document;
import org.bson.conversions.Bson;

import java.util.List;

/**
 * Created by MrDeng on 2017/3/23.
 */
public interface BaseMongo<ID> {

    /**
     * 添加单个
     * @param bson
     */
    void add(Bson bson);

    /**
     * 批量添加
     * @param ts
     */
    void add(List<Bson> ts);

    /**
     * 按ID删除单个
     * @param id
     * @return
     */
    long delete(ID id);

    /**
     * 按条件删除多个
     * @param bson
     * @return
     */
    long delete(Bson bson);

    /**
     * 按ID查询单个
     * @param id
     * @return
     */
    Document findOne(ID id);


    /**
     * 按条件查询单个
     * @param bson
     * @return
     */
    Document findOne(Bson bson);

    FindIterable<Document> find(Bson bson);
}
