package com.iammybest.mq.manager;

/**
 * Created by MrDeng on 2017/2/17.
 * 消息队列 业务类型
 */
public enum MQServiceName {
    USER_UPDATE("user_update"),
    TEST("test");
    private String type;

    MQServiceName(String type) {
        this.type = type;
    }

    public String getType() {
        return this.type;
    }
}
