package com.iammybest.hbase.springboot.hbase.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by yuan on 6/24/15.
 */

public class VehicleStatusValue {

    @Column(name = "gvid")
    private String gvid;

    @Column(name = "oringinalValue")
    private String oringinalValue;

    @Column(name = "value")
    private String value;   //经度

    @Column(name = "signNameZh")
    private String signNameZh;

    @Column(name = "signNameEn")
    private String signNameEn;

    @Column(name = "displayNameZh")
    private String displayNameZh;

    @Column(name = "displayNameEn")
    private String displayNameEn;

    //车辆状态值的类型
    @Column(name = "valueType")
    private String valueType;

    //车辆状态值单位
    @Column(name = "uint")
    private String uint;

    @Column(name = "cateType")
    private String cateType;

    @Column(name = "signalCode")
    private String signalCode;

    public String getSignalCode() {
        return signalCode;
    }

    public void setSignalCode(String signalCode) {
        this.signalCode = signalCode;
    }

    public String getGvid() {
        return gvid;
    }

    public void setGvid(String gvid) {
        this.gvid = gvid;
    }

    public String getOringinalValue() {
        return oringinalValue;
    }

    public void setOringinalValue(String oringinalValue) {
        this.oringinalValue = oringinalValue;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getSignNameZh() {
        return signNameZh;
    }

    public void setSignNameZh(String signNameZh) {
        this.signNameZh = signNameZh;
    }

    public String getSignNameEn() {
        return signNameEn;
    }

    public void setSignNameEn(String signNameEn) {
        this.signNameEn = signNameEn;
    }

    public String getDisplayNameZh() {
        return displayNameZh;
    }

    public void setDisplayNameZh(String displayNameZh) {
        this.displayNameZh = displayNameZh;
    }

    public String getDisplayNameEn() {
        return displayNameEn;
    }

    public void setDisplayNameEn(String displayNameEn) {
        this.displayNameEn = displayNameEn;
    }

    public String getValueType() {
        return valueType;
    }

    public void setValueType(String valueType) {
        this.valueType = valueType;
    }

    public String getUint() {
        return uint;
    }

    public void setUint(String uint) {
        this.uint = uint;
    }

    public String getCateType() {
        return cateType;
    }

    public void setCateType(String cateType) {
        this.cateType = cateType;
    }
}
