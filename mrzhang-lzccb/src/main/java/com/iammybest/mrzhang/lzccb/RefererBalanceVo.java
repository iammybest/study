package com.iammybest.mrzhang.lzccb;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

/**
 * @DESCRIBE:
 * @TIME: 2020/6/20 0:51
 * @AUTHOR: qinghai.deng
 **/
@Slf4j
@Setter
@Getter
@ToString
public class RefererBalanceVo {
    @ExcelColumn(value = "部门",col = 1)
    private String department;
    @ExcelColumn(value = "姓名",col = 2)
    private String name;
    @ExcelColumn(value = "工号",col = 3)
    private String referrer;
    @ExcelColumn(value = "剩余存款余额",col = 4)
    private String balance;
    @ExcelColumn(value = "日均存款余额",col = 5)
    private String perDayBalance;
}
