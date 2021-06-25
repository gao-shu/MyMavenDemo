package com.gaoshu.dao;

import cn.hutool.core.lang.Dict;
import cn.hutool.json.JSONObject;
import com.gaoshu.pojo.User;
import com.gaoshu.pojo.VO.UserVO;
import com.gaoshu.utils.MybatisUtils;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.List;

/**
 * 此类主要是针对简单查询的项目
 */
public class UserTest {
    
    @Test
    public void demo1(){
        // 获取sqlsession对象
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        // 执行sql
        UserDao mapper = sqlSession.getMapper(UserDao.class);
        List<User> userList = mapper.getUserList();
        userList.forEach(System.out::println);
        // 关闭session
        sqlSession.close();
    }

    @Test
    public void demo2(){
        // 获取sqlsession对象
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        // 执行sql
        UserDao mapper = sqlSession.getMapper(UserDao.class);
        User userList = mapper.getUserById(1);
        System.out.println(userList);
        // 关闭session
        sqlSession.close();
    }

    /**
     * 注解
     */
    @Test
    public void demo3(){
        // 获取sqlsession对象
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        // 执行sql
        UserDao mapper = sqlSession.getMapper(UserDao.class);
        JSONObject userById2 = mapper.getUserById2(1);
        System.out.println(userById2);
        // 关闭session
        sqlSession.close();
    }

    /**
     * 注解起别名
     */
    @Test
    public void demo4(){
        // 获取sqlsession对象
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        // 执行sql
        UserDao mapper = sqlSession.getMapper(UserDao.class);
        UserVO userVO = mapper.getUserById3(1);
        System.out.println(userVO);
        // 关闭session
        sqlSession.close();
    }

    /**
     * user包起别名
     */
    @Test
    public void demo5(){
        // 获取sqlsession对象
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        // 执行sql
        UserDao mapper = sqlSession.getMapper(UserDao.class);
        User userVO = mapper.getUserById4(1);
        System.out.println(userVO);
        // 关闭session
        sqlSession.close();
    }

    /**
     * 结果集映射
     */
    @Test
    public void demo6(){
        // 获取sqlsession对象
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        // 执行sql
        UserDao mapper = sqlSession.getMapper(UserDao.class);
        UserVO userVO = mapper.getUserById5(1);
        System.out.println(userVO);
        // 关闭session
        sqlSession.close();
    }

    /**
     * 分页查询
     */
    @Test
    public void demo7(){
        // 获取sqlsession对象
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        // 执行sql
        UserDao mapper = sqlSession.getMapper(UserDao.class);
//        Map<String, Integer> map = new HashMap<>();
//        map.put("index", 0);
//        map.put("page", 3);
//        List<User> userList = mapper.getUserBylimit(map);
        Dict dict = Dict.create();
        dict.set("index", 0).set("page", 3);
        List<User> userList = mapper.getUserBylimit(dict);
        userList.forEach(System.out::println);
        // 关闭session
        sqlSession.close();
    }

    /**
     * 分页查询
     */
    @Test
    public void demo8(){
        // 获取sqlsession对象
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        // 执行sql
        RowBounds rowBounds = new RowBounds(0, 2);
        List<User> userList = sqlSession.selectList("com.gaoshu.mapper.UserMapper.getUserByRoeBounds", null, rowBounds);
        userList.forEach(System.out::println);
        // 关闭session
        sqlSession.close();
    }

    /**
     * 注解开发
     */
    @Test
    public void demo9(){
        // 获取sqlsession对象
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        // 执行sql
        UserDao mapper = sqlSession.getMapper(UserDao.class);
        UserVO userVO = mapper.getUserById6(1);
        System.out.println(userVO);
        // 关闭session
        sqlSession.close();
    }
}
