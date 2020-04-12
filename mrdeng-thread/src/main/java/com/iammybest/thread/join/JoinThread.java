package com.iammybest.thread.join;

import java.util.Date;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * Created by MrDeng on 2017/4/11.
 */
public class JoinThread implements Runnable{

    private String threadName;

    public JoinThread(String threadName){
        this.threadName = threadName;
    }
    public String getThreadName() {
        return threadName;
    }

    public void setThreadName(String threadName) {
        this.threadName = threadName;
    }

    @Override
    public void run() {
        try {
            System.out.printf("Thread:%s start at %s.......",this.threadName,new Date());
            System.out.println();
            Thread.sleep(5);
            System.out.printf("Thread:%s end at %s.......",this.threadName,new Date());
            System.out.println();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Thread th1 = new Thread(new JoinThread("thread-1"));
        Thread th2 = new Thread(new JoinThread("thread-2"));
        Thread th3 = new Thread(new JoinThread("thread-3"));

        try {
            th1.start();
            th1.join();
            th2.start();
            th2.join();
            th3.start();
            th3.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Main thread is finished");


    }
}
