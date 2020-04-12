package com.iammybest.common.http.result;


import com.iammybest.common.http.code.StateCode;

import java.io.Serializable;

/**
 * Created by wms on 2017/2/9.
 *
 * @author WeiMinSheng
 * @version V2.0
 *          Copyright (c)2016 tyj-版权所有
 * @since 2017/2/9
 */
public class BaseResult implements Serializable {
    private static final long serialVersionUID = 1L;

    protected int code;

    // 返回消息，成功为“success”，失败为具体失败信息
    protected String desc;

    public BaseResult() {
        this.code = StateCode.Ok.getCode();
        this.desc = "OK";
    }

    public BaseResult(StateCode stateCode, String desc) {
        this.code = stateCode.getCode();
        this.desc = desc;
    }

    public BaseResult(int stateCode, String desc) {
        this.code = stateCode;
        this.desc = desc;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    /**
     * 构建成功结果对象
     */
    public static BaseResult buildOKResult() {
        return new BaseResult(StateCode.Ok, WebConstants.MSG_OK);
    }

    public static BaseResult build(StateCode stateCode, String desc) {
        return new BaseResult(stateCode, desc);
    }

    public static BaseResult build(int stateCode, String desc) {
        return new BaseResult(stateCode, desc);
    }

    public boolean isOK() {
        return (this.code == StateCode.Ok.getCode());
    }
}
