package com.gaoshu.springbootmybatisplus;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gaoshu.springbootmybatisplus.PO.User;
import com.gaoshu.springbootmybatisplus.service.IUserService;
import javafx.application.Application;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Map;

/**
 * @Title: UserTest
 * @Description: 用户测试
 * @author: gaoshu
 * @date: 2021/11/24 18:28
 */
@SpringBootTest(classes = Application.class)
public class UserQueryOrDeleteTest {
    @Autowired
    private IUserService userService;


    @Test
    public void demo12(){
        userService.removeById(1463461995749392393L);
    }

    @Test
    public void demo2(){
        List<User> list = userService.list();
        list.forEach(System.out::println);
    }

    @Test
    public void demo3(){
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getAge, 20);
        User one = userService.getOne(queryWrapper);
        System.out.println(one);
    }

    @Test
    public void demo4(){
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getAge, 12);
        Map<String, Object> map = userService.getMap(queryWrapper);
        map.forEach(System.out::printf);
    }

    @Test
    public void demo5(){
        Page<User> userPage = new Page<>();
        Page<User> page = userService.page(userPage);
        List<User> records = page.getRecords();
    }

}
