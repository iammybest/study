package com.iammybest.jdk8new.lambda;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created by MrDeng on 2017/4/13.
 */
public class SortArray {
    public static void main(String[] args) {
        List<String> nums = Arrays.asList("p", "b", "f", "e" ,"v", "j");
        Collections.sort(nums,(String a,String b)->a.compareTo(b));
        System.out.println(nums);


    }
}
