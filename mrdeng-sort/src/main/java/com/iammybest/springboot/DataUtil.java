package com.iammybest.springboot;

import java.util.Random;

public class DataUtil {
    public static int[] generateArr(int len){
        int[] datas= new int[len];
        Random random = new Random();
        for (int i = 0;i<len;i++){
            datas[i] = Math.abs(random.nextInt())%len;
        }
        return datas;
    }
    public static void displayArr(int[] datas){
        for (int i = 0;i<datas.length;i++){
            System.out.print(datas[i]+",");
        }
        System.out.println();
    }
}
