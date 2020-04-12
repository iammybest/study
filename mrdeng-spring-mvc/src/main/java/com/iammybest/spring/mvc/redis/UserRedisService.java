package com.iammybest.spring.mvc.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.net.URL;

/**
 * Created by MrDeng on 2017/3/15.
 */
@Service("userRedisService")
public class UserRedisService {
    @Autowired
    private RedisTemplate<String, String> template;

    @Resource(name="redisTemplate")
    private ListOperations<String, String> listOps;

    public void addLink(String userId, String value) {
        listOps.leftPush(userId, value==null?null:value.trim());
    }
    public String getLink(String userId) {
        return listOps.rightPop(userId);
    }

}
