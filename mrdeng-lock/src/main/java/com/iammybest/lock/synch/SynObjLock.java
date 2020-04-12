package com.iammybest.lock.synch;


import lombok.extern.slf4j.Slf4j;

/**
 * Created by MrDeng on 2017/3/21.
 *
 * 此种做法 10个线程各自持有一个锁对象 不存在竞争 锁自然也无效果
 */
@Slf4j
public class SynObjLock implements Runnable{
    private Integer count=0;
    private Integer threadId;

    public SynObjLock(Integer threadId){
        this.threadId= threadId;
    }
    public synchronized void add(){
        this.count++;

    }
    @Override
    public  void run() {
        while (this.count<100){
            add();
            log.info(this.getClass(),"Thread "+this.threadId+" count "+this.count);
        }
    }

    public static void main(String[] args) {
        for(int i=0;i<10;i++){
            new Thread(new SynObjLock(i)).start();
        }
    }
}
