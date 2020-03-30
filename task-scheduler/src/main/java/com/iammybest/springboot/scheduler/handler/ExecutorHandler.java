package com.iammybest.springboot.scheduler.handler;

import com.iammybest.springboot.scheduler.core.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @DESCRIBE:
 * @TIME: 2019/9/24 15:14
 * @AUTHOR: qinghai.deng
 **/
@Service
@EnableAsync
public class ExecutorHandler implements IExecutorHandler {
    Logger logger = LoggerFactory.getLogger(ExecutorHandler.class);
    @Resource
    IAsyncHandler asyncHandler;

    @Override
    public Response excute(int taskId) {
        for (int i = 0; i < 10; i++) {
            logger.info("******submit  task {} ******", i);
            asyncHandler.executeTask(1);
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return Response.create();
    }

}
