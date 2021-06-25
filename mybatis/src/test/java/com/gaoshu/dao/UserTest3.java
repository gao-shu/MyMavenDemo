package com.gaoshu.dao;

import com.gaoshu.pojo.User;
import com.gaoshu.utils.MybatisUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

/**
 * @Title: UserTest3
 * @Description: 主要是动态sql的使用,使用地址：https://mybatis.org/mybatis-3/zh/dynamic-sql.html
 * @author: gaoshu
 * @date: 2021/6/22 15:03
 */
public class UserTest3 {

    /**
     * 一級缓存默认开启
     */
    @Test
    public void demo1(){
        // 获取sqlsession对象
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        // 执行sql
        UserDao mapper = sqlSession.getMapper(UserDao.class);
        User user1 = mapper.getUserById(1);
        System.out.println(user1);

        User user2 = mapper.getUserById(1);
        System.out.println(user2);
        System.out.println(user1 == user2);
        // 关闭session
        sqlSession.close();
    }

    /**
     * 二级缓存手动开启
     */
    @Test
    public void demo2(){
        // 获取sqlsession对象
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        SqlSession sqlSession2 = MybatisUtils.getSqlSession();
        // 执行sql
        UserDao mapper = sqlSession.getMapper(UserDao.class);
        User user1 = mapper.getUserById(1);
        System.out.println(user1);
        sqlSession.close();

        UserDao mapper2 = sqlSession2.getMapper(UserDao.class);
        User user2 = mapper2.getUserById(1);
        System.out.println(user2);
        System.out.println(user1 == user2);
        // 关闭session
        sqlSession2.close();
    }

}
