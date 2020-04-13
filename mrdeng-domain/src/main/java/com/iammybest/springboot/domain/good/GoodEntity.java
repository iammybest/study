package com.iammybest.springboot.domain.good;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * @author iammybest
 * @version 1.0.0
 * @Title: GoodEntity
 * @Description: TODO
 * @date 2020/4/13 23:44
 */
@Entity
public class GoodEntity {
    @Id
    @GeneratedValue
    Long id;

    @Column(name = "name")
    String name;
    @Column(name = "des")
    String des;
}
