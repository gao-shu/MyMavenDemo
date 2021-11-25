package com.gaoshu;

import com.gaoshu.entity.PO.User;
import com.gaoshu.service.IUserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @Title: UserTest
 * @Description: 用户测试
 * @author: gaoshu
 * @date: 2021/11/24 18:28
 */
@SpringBootTest
public class UserAddOrUpdateTest {
    @Autowired
    private IUserService userService;

    /**
     * @Description: 添加，
     *  1.主键id:默认雪花算法，“
     *  2.如何 update 时 column=column+1
     *  3.tinyint使用布尔
     * @Param: []
     * @return: void
     * @Author: gaoshu
     * @Date: 2021/11/25
    */
    @Test
    public void demo1(){
        User user = new User();
//        user.setId(1463461995749392393L);
        user.setName("张三");
        user.setAge(12);
        user.setEmail("123@qq.com");
        user.setDeleted(1);
        user.setSexBit(true);
        userService.addOrUpdateUser(user);
    }

}
