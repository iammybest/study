package com.iammybest.hbase.springboot.hbase.controller;

import com.iammybest.hbase.springboot.hbase.handler.IVehHBaseHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @DESCRIBE:
 * @TIME: 2020/4/29 10:31
 * @AUTHOR: qinghai.deng
 **/
@RestController
@RequestMapping("hbase")
public class HBaseController {

    @Resource
    IVehHBaseHandler vehHBaseHandler;
    @GetMapping("get")
    public String get(){
        vehHBaseHandler.get();
        return "SUCCESS";
    }
}
