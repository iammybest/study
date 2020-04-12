package com.iammybest.springboot.scheduler.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * @DESCRIBE:
 * @TIME: 2019/9/24 16:16
 * @AUTHOR: qinghai.deng
 **/
@Component
public class AsyncHandler implements IAsyncHandler {
    Logger logger = LoggerFactory.getLogger(AsyncHandler.class);
    @Override
    @Async
    public void executeTask(int taskId){
        test(taskId);
    }
    private void test(int taskId){
        for (int index=0;index<30;index++){
            logger.info("******execute  task {} index {} at time {}******",taskId,index,System.currentTimeMillis());
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
