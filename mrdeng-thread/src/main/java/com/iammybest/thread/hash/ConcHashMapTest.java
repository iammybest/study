package com.iammybest.thread.hash;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by MrDeng on 2017/1/11.
 */
public class ConcHashMapTest {
    private static ConcurrentHashMap<String, AtomicLong> map = new ConcurrentHashMap();
    public static  void main(String[] args) {
        for (int i = 0; i < 30; i++) {
            new Thread(new AtomicAdd(map,(i%6+1)+"",i+1)).start();
        }
        new Thread(new ConcHashMapTest().new AtomicTotal(map)).start();
    }

    public static class AtomicAdd implements Runnable {

        ConcurrentHashMap<String, AtomicLong> map;
        String key;
        Integer index;

        public AtomicAdd(ConcurrentHashMap<String, AtomicLong> map, String key, Integer index) {
            if (null == map)
                throw new NullPointerException("Null Map");
            map.put(key, new AtomicLong(0));
            this.map = map;
            this.key = key;
            this.index = index;
        }

        @Override
        public void run() {
            AtomicLong value = map.get(key);
            for (int i = 0; i < 20; i++) {
                value.addAndGet(i + 1);
                System.out.println("Thread " + index + " Key: " + this.key + " After increasing value: " + value);
//                try {
//                    Thread.sleep(100);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
            }
        }
    }
    public class AtomicTotal implements Runnable {

        ConcurrentHashMap<String, AtomicLong> map;

        public AtomicTotal(ConcurrentHashMap<String, AtomicLong> map) {
            if (null == map)
                throw new NullPointerException("Null Map");
            this.map = map;
        }

        @Override
        public void run() {
            if(null==map||this.map.isEmpty())
                return;
            long total = 0;
            for (int i = 0; i < 10; i++) {
                for (AtomicLong value : this.map.values()) {
                    total += value.get();
                }
                System.out.println("Map item Sum: " + total);
//                try {
//                    Thread.sleep(300);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
            }
        }
    }
}
