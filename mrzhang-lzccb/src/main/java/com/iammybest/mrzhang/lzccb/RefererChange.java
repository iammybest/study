package com.iammybest.mrzhang.lzccb;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

/**
 * @DESCRIBE:
 * @TIME: 2020/6/19 22:49
 * @AUTHOR: qinghai.deng
 **/
@Slf4j
@Setter
@Getter
@ToString
public class RefererChange {
    @ExcelColumn(value = "原推荐人",col = 1)
    private String account;
    @ExcelColumn(value = "修正后推荐人",col = 2)
    private String referrer;
}
