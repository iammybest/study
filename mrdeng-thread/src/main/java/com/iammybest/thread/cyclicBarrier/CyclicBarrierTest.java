package com.iammybest.thread.cyclicBarrier;

import java.io.IOException;
import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * CyclicBarrier
 * Created by MrDeng on 2017/4/13.
 */
public class CyclicBarrierTest {

    public static void main(String[] args) throws IOException, InterruptedException {
        //如果将参数改为4，但是下面只加入了3个选手，这永远等待下去
        //Waits until all parties have invoked await on this barrier.
        CyclicBarrier beginBarrier = new CyclicBarrier(3,()->{
            System.out.println("**********all ready*************");
        });
        CyclicBarrier endBarrier = new CyclicBarrier(3,()->{
            System.out.println("**********all end*************");
        });

        ExecutorService executor = Executors.newFixedThreadPool(3);
        executor.submit(new Thread(new Runner(beginBarrier,endBarrier, "1号选手")));
        executor.submit(new Thread(new Runner(beginBarrier, endBarrier,"2号选手")));
        executor.submit(new Thread(new Runner(beginBarrier,endBarrier, "3号选手")));

        executor.shutdown();
    }
}

class Runner implements Runnable {
    // 一个同步辅助类，它允许一组线程互相等待，直到到达某个公共屏障点 (common barrier point)
    private CyclicBarrier beginBarrier;
    private CyclicBarrier endBarrier;
    private String name;

    public Runner(CyclicBarrier beginBarrier,CyclicBarrier endBarrier, String name) {
        super();
        this.beginBarrier = beginBarrier;
        this.endBarrier=endBarrier;
        this.name = name;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(1000 * (new Random()).nextInt(8));
            System.out.println(name + " 准备好了...");
            // barrier的await方法，在所有参与者都已经在此 barrier 上调用 await 方法之前，将一直等待。
            beginBarrier.await();
            System.out.println("起跑 "+System.currentTimeMillis());
            Thread.sleep(1000 * (new Random()).nextInt(8));
            System.out.println(name + " 跑完 等待排名...");
            endBarrier.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        }
    }
}