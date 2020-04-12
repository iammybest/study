package com.iammybest.lock.synch;


/**
 * Created by MrDeng on 2017/3/21.
 */
public class SynStaticMethodLock extends Thread {
    private int threadNo;
    private static Integer count = 1;


    public SynStaticMethodLock(int threadNo) {
        this.threadNo = threadNo;
    }


    public static void main(String[] args) throws Exception {
        String lock = new String("lock");
        for (int i = 1; i < 10; i++) {
            new SynStaticMethodLock(i).start();
            Thread.sleep(1);
        }
    }


    public void run() {
        while (count < 200) {
            synchronized (SynStaticMethodLock.count) {
                System.out.println("No." + threadNo + ":" + count++);
            }
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
