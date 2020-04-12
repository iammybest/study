package com.iammybest.spring.mvc.mongo;

import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.BeforeConvertEvent;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.util.ReflectionUtils;

import javax.annotation.Resource;
import java.lang.reflect.InvocationTargetException;

/**
 * Created by MrDeng on 2017/3/22.
 */
public class SaveMongoEventListener extends AbstractMongoEventListener<Object> {
    @Resource
    private MongoTemplate mongoTemplate;

    public void setMongoTemplate(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    /**
     * 重写父类函数 次函数参数 版本上有变化
     * @param event
     */
    @Override
    public void onBeforeConvert(BeforeConvertEvent<Object> event) {
//        super.onBeforeConvert(event);
        if (event != null&&event.getSource()!=null) {
            ReflectionUtils.doWithFields(event.getSource().getClass(), field -> {
                try {
                    Object invoke = event.getSource().getClass().getDeclaredMethod(
                            "get" + field.getName().substring(0, 1).toUpperCase() + field.getName().substring(1)).invoke(event.getSource());
                    if (!invoke.toString().equals("0")) return;
                } catch (InvocationTargetException | NoSuchMethodException e) {
                    e.printStackTrace();
                }

                ReflectionUtils.makeAccessible(field);
                if (field.isAnnotationPresent(GeneratedValue.class)) {
                    //设置自增ID
                    field.set(event.getSource(), getNextId(event.getSource().getClass().getSimpleName()));
                }
            });
        }
    }
    /**
     * 获取下一个自增ID
     *
     * @param collName 集合名
     * @return
     * @author yinjihuan
     */
    private Long getNextId(String collName) {
        Query query = new Query(Criteria.where("collName").is(collName));
        Update update = new Update();
        update.inc("seqId", 1);
        FindAndModifyOptions options = new FindAndModifyOptions();
        options.upsert(true);
        options.returnNew(true);
        SequenceId seqId = mongoTemplate.findAndModify(query, update, options, SequenceId.class);
        System.out.println("++++++++get new id="+seqId.getSeqId());
        return seqId.getSeqId();
    }
}
