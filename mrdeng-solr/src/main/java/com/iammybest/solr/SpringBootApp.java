package com.iammybest.solr;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;

/**
 * Created by MrDeng on 2017/2/16.
 */
//@Configuration
//@SpringBootApplication
//@ComponentScan(includeFilters = {@ComponentScan.Filter(type = FilterType.ANNOTATION, value = Controller.class),
//        @ComponentScan.Filter(type = FilterType.ANNOTATION, value = ControllerAdvice.class),
//        @ComponentScan.Filter(type = FilterType.ANNOTATION, value = Component.class)})
//public class SpringBootApp {
//
//    @Autowired
//    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
//        auth.inMemoryAuthentication()
//                .withUser("admin").password("123456").roles("USER");
//    }
//
//    public static void main(String[] args) {
//        SpringApplication.run(SpringBootApp.class, args);
//    }
//
//}

@SpringBootApplication
public class SpringBootApp {


    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("admin").password("123456").roles("USER");
    }
    public static void main(String[] args) throws Exception {
        SpringApplication calmapplication=new SpringApplication(SpringBootApp.class);
        calmapplication.run("start");
    }
}