package com.iammybest.jdk8new.definterface;

/**
 * Created by MrDeng on 2017/4/13.
 */
public class Test {
    public static void main(String[] args) {

        Converter<String, Long> converter = Long::valueOf;
        Long converted = converter.convert("123");
        System.out.println(converted);   // 123

    }
}
