package com.gaoshu.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.gaoshu.entity.PO.User;

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author gaoshu
 * @since 2021-11-24
 */
public interface IUserService extends IService<User> {

    /**
     * @Description: 测试接口
     * @Param: []
     * @return: void
     * @Author: gaoshu
     * @Date: 2021/11/24
    */
    public void testUser();

    /**
     * @Description: 添加用户
     * @Param: [user]
     * @return: void
     * @Author: gaoshu
     * @Date: 2021/11/24
    */
    void addOrUpdateUser(User user);

    /**
     * @Description: 删除用户
     * @Param: [userId]
     * @return: void
     * @Author: gaoshu
     * @Date: 2021/11/24
    */
    void deleteUserById(Integer userId);
}
