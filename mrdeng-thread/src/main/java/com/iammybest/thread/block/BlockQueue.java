package com.iammybest.thread.block;


import java.util.ArrayList;
import java.util.List;

/**
 * Created by MrDeng on 2017/4/11.
 */
public class BlockQueue <T>{
    private List<T> bQueue=new ArrayList();
    private int limit=10;
    public BlockQueue(){

    }
    public BlockQueue(int limit){
        this.limit = limit;
    }

    public synchronized  void enqueue(T elem) throws InterruptedException {
        while (this.bQueue.size()==this.limit){
            System.out.println("***********队列满 等待**************");
            wait();
        }
        if(this.bQueue.size()==0){
            System.out.println("***********队列空 唤醒**************");
            notifyAll();
        }
        this.bQueue.add(elem);
    }

    public synchronized T dequeue() throws InterruptedException {
        while (this.bQueue.size()==0){
            System.out.println("***********队列空 等待**************");
            wait();
        }
        if(this.bQueue.size()==this.limit){
            System.out.println("***********队列满 唤醒**************");
            notifyAll();
        }
        return  this.bQueue.remove(0);
    }
}
