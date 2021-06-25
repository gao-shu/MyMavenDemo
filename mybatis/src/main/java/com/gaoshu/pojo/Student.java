package com.gaoshu.pojo;

import lombok.Data;

@Data
public class Student {
    private String student_id;
    private String student_name;
    private String student_birth;
    private String student_sex;
    private String teacher_id;


    private Teacher teacher;


}
