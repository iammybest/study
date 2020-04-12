package com.iammybest.spring.mvc.doc;

import com.iammybest.spring.mvc.mongo.GeneratedValue;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.io.Serializable;

/**
 * Created by MrDeng on 2017/3/22.
 */
@Document(collection = "account")
public class AccountDoc implements Serializable {

    @GeneratedValue
    @Id
    private long id;
    @Field
    private String account;
    @Field
    private String password;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
