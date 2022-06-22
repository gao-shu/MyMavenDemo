package com.example.springbootdemo1.Entity;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class Person {

    private String name;

    private Integer age;

    private String address;

    // 0女 1 男
    private Integer sex;

}
