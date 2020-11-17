package com.iammybest.thread.executor;

import com.iammybest.thread.join.JoinThread;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @DESCRIBE:
 * @TIME: 2020/9/9 16:38
 * @AUTHOR: qinghai.deng
 **/
public class ThreadPoolExecutorTest {
    private static  final Logger logger = LoggerFactory.getLogger(ThreadPoolExecutorTest.class);
    public static void main(String[] args)  {
        ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(2);

        for (int i=0;i<100;i++){

            while (true){
                int overQueue = 10 - executor.getQueue().size();
                if (overQueue > (10>>1)) {
                    logger.info("检查：ThreadPool Status >> [PoolSize: {} ,ActiveCount: {} ,QueueCount: {}]===Normal Break "
                            ,executor.getPoolSize(),executor.getActiveCount(),executor.getQueue().size());
                    //一半队列处于空闲 不校验
                    break;
                }
                if (Thread.currentThread().isInterrupted()) {
                    logger.info("ThreadPool Status >> [PoolSize: {} ,ActiveCount: {} ,QueueCount: {}]===currentThread isInterrupted "
                            ,executor.getPoolSize(),executor.getActiveCount(),executor.getQueue().size());
                    break;
                }
                try {
                    Thread.sleep(500L);
                } catch (InterruptedException e) {
                    logger.info("ThreadPool Status >> [PoolSize: {} ,ActiveCount: {} ,QueueCount: {}]===Sleep InterruptedException "
                            ,executor.getPoolSize(),executor.getActiveCount(),executor.getQueue().size());
                    break;
                }
                logger.info("ThreadPool Status >> [PoolSize: {} ,ActiveCount: {} ,QueueCount: {}] === wait"
                        ,executor.getPoolSize(),executor.getActiveCount(),executor.getQueue().size());
            }
            int finalI = i;
            executor.submit(() -> new JoinThread(finalI +"").run());
        }
        System.exit(0);
    }


}
