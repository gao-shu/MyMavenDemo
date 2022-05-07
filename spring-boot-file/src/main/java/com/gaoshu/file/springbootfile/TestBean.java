package com.gaoshu.file.springbootfile;

import lombok.Data;

import java.util.Date;

/**
 * @author gaoshu
 * @describe TestBean
 * @date 2022/05/06 11:49
 **/
@Data
public class TestBean {

    private String name;
    private int age;
    private double score;
    private boolean isPass;
    private Date examDate;
}
