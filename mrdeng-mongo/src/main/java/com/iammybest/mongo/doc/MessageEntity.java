package com.iammybest.mongo.doc;

import com.iammybest.common.log.LogLevel;
import com.iammybest.common.log.Logger;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


/**
 * 消息实体。
 *
 * @author Ambrose Xu
 */
public class MessageEntity {

    private static final long serialVersionUID = 1L;

    private long serialNumber;

    private int hashCode = 0;

    private String srcTag;
    private long sendTimestamp;
    private long receiveTimestamp;
    private long timestamp;

    // 是否需要加密存储
    private boolean secret = false;

    // 是否是 traceless 状态，此状态下消息仅发送给在线设备
    private boolean traceless = false;

    // 消息是否被撤回，如果消息被撤回，则值为撤回时的时间戳
    private long recall = -1;

    private JSONObject group = null;

    private Receiver receiver;
    private Sender sender;

    // 接收方拉取列表
    private ArrayList<Device> pulledDevices = new ArrayList();

    public ArrayList<Device> getPulledDevices() {
        return pulledDevices;
    }

    public void setPulledDevices(ArrayList<Device> pulledDevices) {
        this.pulledDevices = pulledDevices;
    }

    public Receiver getReceiver() {
        return receiver;
    }

    public void setReceiver(Receiver receiver) {
        this.receiver = receiver;
    }

    public Sender getSender() {
        return sender;
    }

    public void setSender(Sender sender) {
        this.sender = sender;
    }

    public long getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(long serialNumber) {
        this.serialNumber = serialNumber;
    }

    public int getHashCode() {
        return hashCode;
    }

    public void setHashCode(int hashCode) {
        this.hashCode = hashCode;
    }

    public String getSrcTag() {
        return srcTag;
    }

    public void setSrcTag(String srcTag) {
        this.srcTag = srcTag;
    }

    public long getSendTimestamp() {
        return sendTimestamp;
    }

    public void setSendTimestamp(long sendTimestamp) {
        this.sendTimestamp = sendTimestamp;
    }

    public long getReceiveTimestamp() {
        return receiveTimestamp;
    }

    public void setReceiveTimestamp(long receiveTimestamp) {
        this.receiveTimestamp = receiveTimestamp;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public boolean isSecret() {
        return secret;
    }

    public void setSecret(boolean secret) {
        this.secret = secret;
    }

    public boolean isTraceless() {
        return traceless;
    }

    public void setTraceless(boolean traceless) {
        this.traceless = traceless;
    }

    public long getRecall() {
        return recall;
    }

    public void setRecall(long recall) {
        this.recall = recall;
    }

    public JSONObject getGroup() {
        return group;
    }

    public void setGroup(JSONObject group) {
        this.group = group;
    }

    public JSONObject toJSON() {
        JSONObject ret = new JSONObject();
        try {
            ret.put("sn", this.serialNumber);
            ret.put("from", this.getSender().toJSON());
            ret.put("to", this.getReceiver().toJSON());
            if (null != this.group) {
                ret.put("group", this.group);
            }

            JSONObject time = new JSONObject();
            time.put("send", this.sendTimestamp);
            time.put("receive", this.receiveTimestamp);
            time.put("timestamp", this.timestamp);
            ret.put("time", time);

            ret.put("secret", this.secret);
            ret.put("traceless", this.traceless);

            if (this.recall > 0) {
                ret.put("recall", this.recall);
            }
            synchronized (this.pulledDevices) {
                if (!this.pulledDevices.isEmpty()) {
                    JSONArray array = new JSONArray();
                    for (Device device : this.pulledDevices) {
                        array.put(device.toJSON());
                    }
                    ret.put("pulledDevices", array);
                }
            }
        } catch (JSONException e) {
            Logger.log(this.getClass(), e, LogLevel.WARNING);
        }
        return ret;
    }

}
