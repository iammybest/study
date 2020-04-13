package com.iammybest.springboot.domain.user;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * @author iammybest
 * @version 1.0.0
 * @Title: UserEntity
 * @Description: TODO
 * @date 2020/4/13 23:43
 */
@Entity
public class UserEntity {
    @Id
    @GeneratedValue
    Long id;

    @Column(name = "name")
    String name;
    @Column(name = "age")
    Integer age;
}
