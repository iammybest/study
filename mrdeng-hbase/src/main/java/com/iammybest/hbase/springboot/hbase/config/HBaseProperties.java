package com.iammybest.hbase.springboot.hbase.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @Auther: david
 * @Date: 2019/1/8
 * @Description: todo
 */
@ConfigurationProperties("rtm-vehicle-status.hbase")
@Setter
@Getter
public class HBaseProperties {
    String zkQuorum;
    String master;
    String clientPort;
    String tenantId;
    String vehicleDiagnosisTable;
    String  vehiceStatusTable;
    String krb5ConfPath;
    String userName;
    String userKeytabPath;
    String hbaseSitePath;

}
