package com.iammybest.springboot.collection;

public class RuntimeLeanning {
    public static void main(String[] args) {
        Runtime run = Runtime.getRuntime();
        System.out.println();
        System.out.println("2:MAX = " + run.maxMemory());
        System.out.println("2:TOTAL = " + run.totalMemory());
        System.out.println("2:FREE = " + run.freeMemory());
        String str = "";
        for (int x = 0; x < 2000; x++) {
            str += x; // 产生大量垃圾
        }

        System.out.println();
        System.out.println("2:MAX = " + run.maxMemory());
        System.out.println("2:TOTAL = " + run.totalMemory());
        System.out.println("2:FREE = " + run.freeMemory());

        run.gc();// 释放垃圾空间
        System.out.println();

        System.out.println("2:MAX = " + run.maxMemory());
        System.out.println("2:TOTAL = " + run.totalMemory());
        System.out.println("2:FREE = " + run.freeMemory());
    }
}
