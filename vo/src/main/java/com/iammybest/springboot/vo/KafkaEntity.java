package com.iammybest.springboot.vo;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * @DESCRIBE:
 * @TIME: 2020/1/17 14:59
 * @AUTHOR: qinghai.deng
 **/
@Setter
@Getter
@ToString
public class KafkaEntity implements Serializable {
    private String name;
    private Integer age;
    private String sex;

    public KafkaEntity() {
    }

    public KafkaEntity(String name, Integer age, String sex) {
        this.name = name;
        this.age = age;
        this.sex = sex;
    }

}
