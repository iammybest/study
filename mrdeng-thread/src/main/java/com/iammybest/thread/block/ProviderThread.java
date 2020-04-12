package com.iammybest.thread.block;

import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by MrDeng on 2017/4/11.
 */
public class ProviderThread implements Runnable {

    private BlockQueue bq;

    public ProviderThread(BlockQueue bq) {
        this.bq = bq;
    }

    @Override
    public void run() {
        for (int i = 0; i < 20; i++) {
            try {
                System.out.println("*****Provide message : " + i);
                bq.enqueue(1 + "");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        BlockQueue<String> blockQueue =new BlockQueue<>();
        new Thread(new ConsumerThread(blockQueue)).start();
        new Thread(new ProviderThread(blockQueue)).start();
        ConcurrentHashMap map =new ConcurrentHashMap();
    }
}
