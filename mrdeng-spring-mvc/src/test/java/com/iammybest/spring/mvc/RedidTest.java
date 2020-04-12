package com.iammybest.spring.mvc;

import com.iammybest.spring.mvc.redis.UserRedisService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.net.MalformedURLException;

/**
 * Created by MrDeng on 2017/3/15.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
        "classpath:mrdeng-spring-mvc-servlet.xml",
        "classpath:spring-mybatis.xml" ,
        "classpath:spring-redis.xml"
})
public class RedidTest       {

    @Autowired
    UserRedisService userRedisService;
    @Test
    public void test() throws MalformedURLException {
        userRedisService.addLink("key","ddddddd");
//        Logger.i(this.getClass(),userRedisService.getLink("key"));
        System.out.println("****************************"+userRedisService.getLink("key"));
//        Logger.i(this.getClass(),userRedisService.getLink("key"));
        System.out.println("****************************"+userRedisService.getLink("key"));
    }

}
