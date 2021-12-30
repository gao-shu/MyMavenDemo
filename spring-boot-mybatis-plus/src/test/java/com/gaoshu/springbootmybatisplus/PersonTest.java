package com.gaoshu.springbootmybatisplus;

import com.gaoshu.springbootmybatisplus.PO.Person;
import com.gaoshu.springbootmybatisplus.service.IPersonService;
import javafx.application.Application;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

/**
 * @Title: PersonTest
 * @Description: 测试
 * @author: gaoshu
 * @date: 2021/12/22 19:45
 */
@SpringBootTest(classes = Application.class)
public class PersonTest {

    @Autowired
    private IPersonService personService;

    @Test
    public void demo1(){
        List<Person> list = personService.list();
        System.out.println(list);
    }
}
