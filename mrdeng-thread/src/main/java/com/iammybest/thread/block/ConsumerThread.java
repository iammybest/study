package com.iammybest.thread.block;

/**
 * Created by MrDeng on 2017/4/11.
 */
public class ConsumerThread implements Runnable{

    private BlockQueue<String> bq ;

    public ConsumerThread(BlockQueue bq){
        this.bq = bq;
    }
    @Override
    public void run() {
        for (int i = 0;i<20;i++){
            try {
                System.out.println("*****Consume message : "+bq.dequeue());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
