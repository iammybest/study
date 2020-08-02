package com.iammybest.mrzhang.lzccb;

import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import lombok.Getter;
import lombok.Setter;
/**
 * @DESCRIBE:
 * @TIME: 2020/6/19 22:39
 * @AUTHOR: qinghai.deng
 **/
@Slf4j
@Setter
@Getter
@ToString
public class Deposit {
    @ExcelColumn(value = "客户名称",col = 1)
    private String accountName;
    @ExcelColumn(value = "身份证号码",col = 2)
    private String idNumber;
    @ExcelColumn(value = "开户日期",col = 3)
    private String date;
    @ExcelColumn(value = "存款账号",col = 4)
    private String account;
    @ExcelColumn(value = "子帐户序号",col = 5)
    private String subAccount;
    @ExcelColumn(value = "存款产品种类",col = 6)
    private String accountType;
    @ExcelColumn(value = "存款余额",col = 7)
    private Double balance;
    @ExcelColumn(value = "日均存款余额",col = 8)
    private Double perDayBalance;
    @ExcelColumn(value = "推荐人1",col = 9)
    private String referrer;
    @ExcelColumn(value = "推荐人2",col = 10)
    private String referrerTemp;
    @ExcelColumn(value = "归入营业部规则",col = 11)
    private String role;

    public String display(){
        return this.idNumber+",\t"+balance+",\t"+referrer;
    }
}
