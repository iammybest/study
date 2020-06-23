package com.iammybest.mrzhang.lzccb;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @DESCRIBE:
 * @TIME: 2020/6/21 3:03
 * @AUTHOR: qinghai.deng
 **/
@RestController
@RequestMapping("lzccb")
public class ReportController {
    @Resource
    StatisticsHandler statisticsHandler;
    @GetMapping("statistics")
    public String statistics(@RequestParam("sourceFileName")String sourceFileName){
        return statisticsHandler.statistics(sourceFileName);
    }
    @GetMapping("report")
    public String report(@RequestParam("firstSourceFileName")String firstSourceFileName,
                         @RequestParam("secondSourceFileName")String secondSourceFileName){

        return statisticsHandler.report(firstSourceFileName,secondSourceFileName);
    }

}