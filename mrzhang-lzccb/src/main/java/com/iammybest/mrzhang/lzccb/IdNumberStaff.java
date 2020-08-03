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
public class IdNumberStaff {
    @ExcelColumn(value = "账号",col = 1)
    private String idNumber;
    @ExcelColumn(value = "推荐人",col = 2)
    private String referrer;

    public IdNumberStaff() {
    }

    public IdNumberStaff(String idNumber, String referrer) {
        this.idNumber = idNumber;
        this.referrer = referrer;
    }
}
