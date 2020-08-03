package com.iammybest.springboot.hbase;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author iammybest
 * @version 1.0.0
 * @Title: LockApplication
 * @Description: TODO
 * @date 2020/4/8 22:11
 */
@Slf4j
@SpringBootApplication
public class HbaseApplication {
        public static void main(String[] args) {
            SpringApplication.run(HbaseApplication.class, args);
        }
}
