package com.iammybest.springboot.actuator;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
@Slf4j
public class MrdengActuatorApplication {

    public static void main(String[] args) {
        SpringApplication.run(MrdengActuatorApplication.class, args);
        log.debug("*********SpringBoot Actuator**********");
    }

}
