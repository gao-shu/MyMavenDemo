package com.gaoshu.service.impl;

import com.gaoshu.entity.PO.User;
import com.gaoshu.mapper.UserMapper;
import com.gaoshu.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author gaoshu
 * @since 2021-11-25
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

}
