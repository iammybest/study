package com.iammybest.mrzhang.lzccb;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

/**
 * @DESCRIBE:
 * @TIME: 2020/6/21 1:48
 * @AUTHOR: qinghai.deng
 **/
@Slf4j
@Setter
@Getter
@ToString
public class ReportVo {
    @ExcelColumn(value = "部门",col = 1)
    private String department;
    @ExcelColumn(value = "姓名",col = 2)
    private String name;
    @ExcelColumn(value = "工号",col = 3)
    private String referrer;
    @ExcelColumn(value = "第一组剩余存款余额",col = 4)
    private String firstBalance;
    @ExcelColumn(value = "第一组部门剩余存款余额",col = 5)
    private String firstTotalBalance;
    @ExcelColumn(value = "第一组日均存款余额",col = 6)
    private String  firstPerDayBalance;
    @ExcelColumn(value = "第一组部门日均存款余额",col = 7)
    private String  firstTotalPerDayBalance;
    @ExcelColumn(value = "第二组剩余存款余额",col = 8)
    private String secondBalance;
    @ExcelColumn(value = "第二组部门剩余存款余额",col = 9)
    private String secondTotalBalance;
    @ExcelColumn(value = "第二组日均存款余额",col = 10)
    private String secondPerDayBalance;
    @ExcelColumn(value = "第二组部门日均存款余额",col = 11)
    private String secondTotalPerDayBalance;
    @ExcelColumn(value = "第一组与第二组存款余额差值",col = 12)
    private String diffBalance;
    @ExcelColumn(value = "部门第一组与第二组存款余额差值总和",col = 13)
    private String totalDiffBalance;
    @ExcelColumn(value = "第一组与第二组存款余额差值",col = 14)
    private String diffPerDayBalance;
    @ExcelColumn(value = "部门第一组与第二组存款余额差值总和",col = 15)
    private String totalDiffPerDayBalance;
}
