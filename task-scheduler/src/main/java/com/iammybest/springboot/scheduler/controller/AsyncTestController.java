package com.iammybest.springboot.scheduler.controller;

import com.iammybest.springboot.scheduler.core.Response;
import com.iammybest.springboot.scheduler.handler.IExecutorHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @DESCRIBE:
 * @TIME: 2019/9/24 14:41
 * @AUTHOR: qinghai.deng
 **/
@RestController
@RequestMapping(value = "test/async")
public class AsyncTestController {
    private static final Logger logger = LoggerFactory.getLogger(AsyncTestController.class);
    @Resource
    IExecutorHandler executorHandler;
    @GetMapping("excute")
    public Response excute() {
        executorHandler.excute(1);
        return new Response();
    }
}
