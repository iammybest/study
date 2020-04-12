package com.iammybest.common.http.result;


import com.iammybest.common.http.code.StateCode;

public class DataResult<T> extends BaseResult {

    private T data;

    public T getData() {
        return data;
    }

    public DataResult<T> setData(T data) {
        this.data = data;
        return this;
    }

    public DataResult() {
        this.code = StateCode.Ok.getCode();
        this.desc = WebConstants.MSG_OK;
    }

    public DataResult(String msg) {
        this.code = StateCode.Ok.getCode();
        this.desc = msg;
    }

    public DataResult(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public DataResult(StateCode stateCode, String desc) {
        this.code = stateCode.getCode();
        this.desc = desc;
    }

    public DataResult(StateCode stateCode, String desc, T data) {
        this.code = stateCode.getCode();
        this.desc = desc;
        this.data = data;
    }
}
