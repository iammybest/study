package com.iammybest.thread;

/**
 * Created by MrDeng on 2016/12/15.
 */
public interface IWork {
    default int getWorkNumber(){
        return 0;
    }
}
