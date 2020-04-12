package com.iammybest.jdk8new.definterface;

/**
 * Created by MrDeng on 2017/4/13.
 */
@FunctionalInterface
public interface Converter<F ,T> {
    T convert(F from);
}
