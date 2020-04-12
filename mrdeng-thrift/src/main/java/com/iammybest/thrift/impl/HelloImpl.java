package com.iammybest.thrift.impl;

import com.iammybest.thrift.Hello;
import org.apache.thrift.TException;

/**
 * Created by MrDeng on 2016/9/27.
 */
public class HelloImpl implements Hello.Iface{

    @Override
    public String hello(String para) throws TException {
        return "Hello:"+para;
    }
}
