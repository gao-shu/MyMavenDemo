package com.example.springbootdemo1.Enum;

import lombok.Getter;

/**
 * @author gaoshu
 */

@Getter
public enum DemoEnum {

    A(1, "A"),
    B(2, "B"),
    C(3, "C"),
    A1(4, "A+"),
    A2(5, "A"),
    A3(6, "A-"),
    B1(7, "B+"),
    B2(8, "B"),
    B3(9, "B-"),
    C1(10, "C+"),
    C2(11, "C"),
    C3(12, "C-");

    private Integer code;//等级代码
    private String level;//等级

    DemoEnum(Integer code, String level) {
        this.code = code;
        this.level = level;
    }


}
