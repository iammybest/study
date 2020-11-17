package com.iammybest.springboot.quartz;

import lombok.extern.slf4j.Slf4j;
import org.quartz.Scheduler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Hello world!
 *
 */
@SpringBootApplication
@Slf4j
public class QuartzApp
{

    public static void main(String[] args) {
        SpringApplication.run(QuartzApp.class, args);
        log.info("【【【【【【定时任务分布式节点 - quartz-cluster-node-second 已启动】】】】】】");
    }
}
