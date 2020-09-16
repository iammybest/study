package com.iammybest.study.shutdown;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@SpringBootApplication
@RestController
@RequestMapping("/")
public class MrdengShutdownApplication {

    public static void main(String[] args) {
        SpringApplication.run(MrdengShutdownApplication.class, args);
    }

    @GetMapping("shutdown")
    public String shutdow() throws InterruptedException {
        Thread.sleep(10*1000);
        return "Shutdown OK"+new Date().toString();
    }
}
