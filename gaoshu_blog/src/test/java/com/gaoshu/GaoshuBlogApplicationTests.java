package com.gaoshu;

import com.gaoshu.entity.PO.User;
import com.gaoshu.service.IUserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class GaoshuBlogApplicationTests {

    @Autowired
    private IUserService userService;

    @Test
    void contextLoads() {

    }

    @Test
    public void demo1(){
        List<User> list = userService.lambdaQuery().list();
        list.forEach(System.out::println);
    }

}
