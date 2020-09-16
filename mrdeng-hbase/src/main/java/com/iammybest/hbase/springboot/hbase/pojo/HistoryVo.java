package com.iammybest.hbase.springboot.hbase.pojo;

import java.io.Serializable;

/**
 * @author: LiuBangChao
 * @Date: 2017/4/21
 * @Time: 17:21
 */
public class HistoryVo implements Serializable {

    private String gvid;
    private String name;
    private String value;
    private String unit;

    public String getGvid() {
        return gvid;
    }

    public void setGvid(String gvid) {
        this.gvid = gvid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    @Override
    public String toString() {
        return "HistoryVo{" +
                "gvid='" + gvid + '\'' +
                ", name='" + name + '\'' +
                ", value='" + value + '\'' +
                ", unit='" + unit + '\'' +
                '}';
    }
}
