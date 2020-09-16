package com.iammybest.thread.join;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Random;

/**
 * Created by MrDeng on 2017/4/11.
 */

public class JoinThread implements Runnable{
    private static  final Logger logger = LoggerFactory.getLogger(JoinThread.class);
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
    static final Random r = new Random();
    @Override
    public void run() {
        try {
            logger.info("Thread:{} start at {}",this.threadName,System.currentTimeMillis());
            long sleep = r.nextInt(3000);
            Thread.sleep(sleep);
            logger.info("Thread:{} end at {},Spend time:{}",this.threadName,System.currentTimeMillis(),sleep);
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
