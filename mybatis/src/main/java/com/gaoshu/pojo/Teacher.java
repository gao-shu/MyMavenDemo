package com.gaoshu.pojo;

import lombok.Data;

import java.util.List;

@Data
public class Teacher {
    private String teacher_id;
    private String teacher_name;

    private List<Student> studentList;
}
