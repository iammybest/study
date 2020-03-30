package com.iammybest.springboot.scheduler.core;

/**
 * @DESCRIBE:
 * @TIME: 2019/9/24 15:12
 * @AUTHOR: qinghai.deng
 **/
public interface IError {
    String getNamespace();

    String getErrorCode();

    String getErrorMessage();
}
