package com.iammybest.hbase.springboot.hbase.config;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @DESCRIBE:
 * @TIME: 2020/4/29 10:58
 * @AUTHOR: qinghai.deng
 **/
@Configuration
@EnableConfigurationProperties({HBaseProperties.class})
public class HBaseConfigure {


}
