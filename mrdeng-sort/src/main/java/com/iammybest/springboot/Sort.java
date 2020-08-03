package com.iammybest.springboot;

public class Sort {
    public static void bubbleSort(int[] datas){
        System.out.println("原始数据");
        DataUtil.displayArr(datas);
        System.out.println("开始冒泡排序");
        long start = System.currentTimeMillis();
        long exchangeTimes = 0;
        for(int i =0;i<datas.length-1;i++){
            for(int k=0;k<datas.length-i-1;k++){
                if(datas[k]>datas[k+1]){
                    int temp = datas[k];
                    datas[k]=datas[k+1];
                    datas[k+1]=temp;
                    exchangeTimes++;
                }
            }
        }
        System.out.println("耗时:"+(System.currentTimeMillis()-start)+" 交换次数："+exchangeTimes);
        System.out.println("排序后数据");
        DataUtil.displayArr(datas);
    }

    public static void SelectionSort(int[] datas){
        System.out.println("原始数据");
        DataUtil.displayArr(datas);
        System.out.println("开始选择排序");
        long start = System.currentTimeMillis();
        long exchangeTimes = 0;
        for(int i =0;i<datas.length;i++){
            int minIndex=i;
            for(int k=i+1;k<datas.length;k++){
                if(datas[minIndex]>datas[k]){
                    minIndex=k;
                }
            }
            if(minIndex!=i){
                exchangeTimes++;
                int temp = datas[i];
                datas[i]=datas[minIndex];
                datas[minIndex]=temp;
            }
        }
        System.out.println("耗时:"+(System.currentTimeMillis()-start)+" 交换次数："+exchangeTimes);
        System.out.println("排序后数据");
        DataUtil.displayArr(datas);
    }

    public static void shellSort(int[] datas){
        System.out.println("原始数据");
        DataUtil.displayArr(datas);
        System.out.println("开始选择排序");
        long start = System.currentTimeMillis();
        long exchangeTimes = 0;
        int len = datas.length;
        for(int gap = (int)Math.floor(len / 2); gap > 0; gap = (int)Math.floor(gap / 2)) {
            System.out.println("本趟："+gap);
            for(int i = gap; i < len; i++) {
                int j = i;
                int current = datas[i];
                System.out.println("本次："+gap);
                while(j - gap >= 0 && current < datas[j - gap]) {
                    datas[j] = datas[j - gap];
                    DataUtil.displayArr(datas);
                    j = j - gap;
                    exchangeTimes++;
                }
                datas[j] = current;
                DataUtil.displayArr(datas);
            }
        }
        System.out.println("耗时:"+(System.currentTimeMillis()-start)+" 交换次数："+exchangeTimes);
        System.out.println("排序后数据");
        DataUtil.displayArr(datas);
    }
}
