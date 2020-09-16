package com.iammybest.study.actuator;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("log")
@Slf4j
public class LogController {
    @GetMapping("print")
    public String printLog(@RequestParam("context")String context){
        log.trace("trace:{}",context);
        log.debug("debug:{}",context);
        log.info("info:{}",context);
        log.warn("warn:{}",context);
        log.error("error:{}",context);
        return context;
    }
}
