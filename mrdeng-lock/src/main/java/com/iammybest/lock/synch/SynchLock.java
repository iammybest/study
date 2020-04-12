package com.iammybest.lock.synch;

/**
 * Created by MrDeng on 2017/3/21.
 */
public class SynchLock extends Thread {

    private int threadNo;
    private static Integer count = 1;


    public SynchLock(int threadNo) {
        this.threadNo = threadNo;
    }


    public static void main(String[] args) throws Exception {
        String lock = new String("lock");
        for (int i = 1; i < 10; i++) {
            new SynchLock(i).start();
            Thread.sleep(1);
        }
    }


    public void run() {
        while (count < 100) {
            synchronized (SynchLock.count) {
                System.out.println("No." + threadNo + ":" + count++);
            }
        }
    }
}
