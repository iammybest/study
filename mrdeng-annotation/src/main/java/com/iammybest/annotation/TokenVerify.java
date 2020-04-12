package com.iammybest.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

/**
 * Created by MrDeng on 2017/2/24.
 * Token验证注解
 */

@Target(ElementType.METHOD)
public @interface TokenVerify {
    String token();
}
