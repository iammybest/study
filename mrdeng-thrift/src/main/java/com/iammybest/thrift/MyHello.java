package com.iammybest.thrift;

import org.apache.thrift.TException;

/**
 * Created by MrDeng on 2016/10/8.
 */
public interface MyHello {
    public String hello(String para) throws TException;
}
