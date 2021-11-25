package com.gaoshu.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gaoshu.entity.PO.User;
import com.gaoshu.mapper.UserMapper;
import com.gaoshu.service.IUserService;
import org.springframework.stereotype.Service;

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
        System.out.println(123);
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
