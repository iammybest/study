package com.iammybest.springboot.scheduler.handler;

/**
 * @DESCRIBE:
 * @TIME: 2019/9/24 16:16
 * @AUTHOR: qinghai.deng
 **/
public interface IAsyncHandler {

    void executeTask(int taskId);
}
