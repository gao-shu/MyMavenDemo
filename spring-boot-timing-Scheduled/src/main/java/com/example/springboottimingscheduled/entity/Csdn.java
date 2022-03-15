package com.example.springboottimingscheduled.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Csdn {

    /**
     * id
     */
    private int id;
    /**
     * 文章标题
     */
    private String title;
    /**
     * 文章发布时间
     */
    private String time;
    /**
     * 文章所属分类
     */
    private String category;
    /**
     * 文章内容
     */
    private String content;

}