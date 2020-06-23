package com.iammybest.annotation;

import java.lang.annotation.*;

/**
 * @DESCRIBE:
 * @TIME: 2020/4/23 0:11
 * @AUTHOR: qinghai.deng
 **/
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface AsyncTask {
}
