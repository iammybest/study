package com.iammybest.mq.message;

import java.io.Serializable;

/**
 * Created by MrDeng on 2017/2/18.
 */
public abstract class MqMessage implements Serializable {
    /**
     * 消息指令
     */
    private String command;

//    private String tag;
//    private Long startDeliverTime =0L;

    private MqMessage() {

    }

    public MqMessage(String command) {
//        this.tag=tag;
        this.command = command;
    }

    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }

//    public String getTag() {
//        return tag;
//    }
//
//    public void setTag(String tag) {
//        this.tag = tag;
//    }

//    public Long getStartDeliverTime() {
//        return startDeliverTime;
//    }
//
//    public void setStartDeliverTime(Long startDeliverTime) {
//        this.startDeliverTime = startDeliverTime;
//    }

    @Override
    public abstract String toString();
}
