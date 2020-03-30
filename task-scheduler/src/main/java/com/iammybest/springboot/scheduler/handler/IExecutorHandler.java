package com.iammybest.springboot.scheduler.handler;

import com.iammybest.springboot.scheduler.core.Response;

/**
 * @DESCRIBE:
 * @TIME: 2019/9/24 15:14
 * @AUTHOR: qinghai.deng
 **/
public interface IExecutorHandler {
    Response excute(int taskId);

}
