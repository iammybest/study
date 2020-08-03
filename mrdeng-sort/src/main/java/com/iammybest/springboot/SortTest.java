package com.iammybest.springboot;

public class SortTest {
    public static void main(String[] args) {

//        Sort.bubbleSort(DataUtil.generateArr(10000));
//        Sort.SelectionSort(DataUtil.generateArr(10000));
        Sort.shellSort(DataUtil.generateArr(10));
    }

}
