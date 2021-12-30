package com.gaoshu.springboothutool.contant;

import lombok.Getter;

@Getter
public enum SexEnum {


    MAN(0, "男"),
    WOMAM(1, "女");

    SexEnum(Integer code, String sex){
        this.code = code;
        this.sex = sex;
    }

    private Integer code;
    private String sex;
}
