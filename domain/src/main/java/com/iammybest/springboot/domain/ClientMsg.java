package com.iammybest.springboot.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @DESCRIBE:
 * @TIME: 2019/11/22 10:55
 * @AUTHOR: qinghai.deng
 **/

@Entity
@Setter
@Getter
@Table(name = "gdb_client_send_msg")
public class ClientMsg {
    String uniqueId;
    String msg;
    Integer resendTimes;
    String vin;
}
