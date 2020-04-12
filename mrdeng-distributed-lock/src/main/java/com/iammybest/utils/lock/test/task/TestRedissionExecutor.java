package com.iammybest.utils.lock.test.task;

import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * @author iammybest
 * @version 1.0.0
 * @Title: TestRedissionExecutor
 * @Description: TODO
 * @date 2020/4/10 22:30
 */
@Service
@Slf4j
public class TestRedissionExecutor {
    private static final String  LOCK_NAME = "MY_LOCK";
    @Resource
    RedissonClient redisson;
    @Async
    @Scheduled(fixedDelay = 1000000L)
    public void testRedis1(){
        String name = "AA";
        RLock lock = redisson.getLock(LOCK_NAME);
        for (int i =1 ;i<=10;i++){
            lock.lock();
            doWork(name,i);
            lock.unlock();
            log.info("{}--【{}】释放锁",name,i);
        }

    }
    @Async
    @Scheduled(fixedDelay = 1000000L)
    public void testRedis2(){
        String name = "BB";
        RLock lock =redisson.getLock("MyLock");
        for (int i =1 ;i<=10;i++){
            lock.lock(10, TimeUnit.SECONDS);
            // 尝试加锁，最多等待100秒，上锁以后10秒自动解锁
            try {
                boolean res = lock.tryLock(100, 10, TimeUnit.SECONDS);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            doWork(name,i);
            lock.unlock();
            log.info("{}--【{}】释放锁",name,i);
        }

    }

    private void doWork(String name,int workId){
        Random r = new Random();
        long sleep = r.nextInt(3000);
        log.info("{}--【{}】获取到锁 开始工作",name,workId);
        try {
            Thread.sleep(sleep);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info("{}--【{}】工作完成",name,workId);

    }
}
