package com.gaoshu.springbootmybatisplus.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gaoshu.springbootmybatisplus.PO.User;
import com.gaoshu.springbootmybatisplus.mapper.UserMapper;
import com.gaoshu.springbootmybatisplus.service.IUserService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author gaoshu
 * @since 2021-11-24
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    @Override
    public void testUser() {
//        System.out.println(123);
        List<User> list = list();
    }

    @Override
    public void addOrUpdateUser(User user) {
        saveOrUpdate(user);
    }

    @Override
    public void deleteUserById(Integer userId) {
        removeById(userId);
    }
}
