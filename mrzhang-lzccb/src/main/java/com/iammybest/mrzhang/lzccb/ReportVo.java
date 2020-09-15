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
    private Double firstBalance;
    @ExcelColumn(value = "第一组部门剩余存款余额",col = 5)
    private Double firstTotalBalance;
//    @ExcelColumn(value = "第一组日均存款余额",col = 6)
//    private Double  firstPerDayBalance;
//    @ExcelColumn(value = "第一组部门日均存款余额",col = 7)
//    private Double  firstTotalPerDayBalance;
    @ExcelColumn(value = "第二组剩余存款余额",col = 6)
    private Double secondBalance;
    @ExcelColumn(value = "第二组部门剩余存款余额",col = 7)
    private Double secondTotalBalance;
//    @ExcelColumn(value = "第二组日均存款余额",col = 10)
//    private Double secondPerDayBalance;
//    @ExcelColumn(value = "第二组部门日均存款余额",col = 11)
//    private Double secondTotalPerDayBalance;
    @ExcelColumn(value = "第一组与第二组存款余额差值",col = 8)
    private Double diffBalance;
    @ExcelColumn(value = "部门第一组与第二组存款余额差值总和",col = 9)
    private Double totalDiffBalance;
//    @ExcelColumn(value = "第一组与第二组存款余额差值",col = 14)
//    private Double diffPerDayBalance;
//    @ExcelColumn(value = "部门第一组与第二组存款余额差值总和",col = 15)
//    private Double totalDiffPerDayBalance;
}
