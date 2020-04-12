package com.iammybest.mq.message;

import com.alibaba.fastjson.JSON;

/**
 * Created by MrDeng on 2017/2/18.
 */
public class UserMqMessage extends MqMessage {

    Long userId;
    String userName;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public UserMqMessage(String command) {
        super(command);
    }

    public UserMqMessage(String command, Long userId, String userName) {
        super(command);
        this.userId = userId;
        this.userName = userName;
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}
