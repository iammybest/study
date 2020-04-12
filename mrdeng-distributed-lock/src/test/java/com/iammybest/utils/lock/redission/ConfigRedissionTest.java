package com.iammybest.utils.lock.redission;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.redisson.Redisson;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.redisson.config.TransportMode;

import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author iammybest
 * @version 1.0.0
 * @Title: ConfigRedissionTest
 * @Description: TODO
 * @date 2020/4/8 22:16
 */
@Slf4j
public class ConfigRedissionTest {

    @Test
    public void testConfig() throws InterruptedException {
        log.info("测试配置方式 使用Redission");
        ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(11, 11, 1000, TimeUnit.MILLISECONDS, new SynchronousQueue<Runnable>(),Executors.defaultThreadFactory(),new ThreadPoolExecutor.AbortPolicy());new ThreadPoolExecutor(1, 2, 1000, TimeUnit.MILLISECONDS,
                new SynchronousQueue<Runnable>(), Executors.defaultThreadFactory(),new ThreadPoolExecutor.AbortPolicy());
        for(int i=0;i<11;i++) {
            poolExecutor.execute(new LockJob(i));
        }
        Thread.sleep(200000L);
    }

    public class LockJob implements Runnable{

        private int jobId;

        public LockJob(int jobId) {
            this.jobId = jobId;
        }

        @Override
        public void run() {
            RLock lock = getRedissionLock(LOCK_NAME);
            Random r = new Random();
            long sleep = r.nextInt(3000);
            log.info("{}尝试获取锁",jobId);
            lock.lock();
            log.info("{}获取到锁 开始工作",jobId);
            try {
                Thread.sleep(sleep);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            log.info("{}工作完成",jobId);
            lock.unlock();
            log.info("{}释放锁",jobId);

        }
    }

    private static final String  LOCK_NAME = "MY_LOCK";
    public static RedissonClient redisson ;
    static {
        log.info("测试配置方式 使用Redission");
        Config config = new Config();
        config.setTransportMode(TransportMode.NIO);
        config.useSingleServer().setDatabase(0).setAddress("redis://192.168.101.9:6379");
//        config.useClusterServers()
//                //可以用"rediss://"来启用SSL连接
//                .addNodeAddress("redis://192.168.101.9:6379");
        redisson = Redisson.create(config);
    }
    public static RLock getRedissionLock(String lockName){
        RLock lock = redisson.getLock(lockName);
        return lock;
    }
}
