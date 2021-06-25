package com.gaoshu.dao;

import com.gaoshu.pojo.Student;
import com.gaoshu.pojo.Teacher;
import com.gaoshu.utils.MybatisUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

/**
 * 此类主要是针对一对多和多对一的查询
 */
import java.util.List;

public class UserTest2 {

    @Test
    public void demo1(){
        // 获取sqlsession对象
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        // 执行sql
        StudentDao mapper = sqlSession.getMapper(StudentDao.class);
        List<Student> studentList = mapper.getStudentList();
        studentList.forEach(System.out::println);
        // 关闭session
        sqlSession.close();
    }

    @Test
    public void demo2(){
        // 获取sqlsession对象
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        // 执行sql
        StudentDao mapper = sqlSession.getMapper(StudentDao.class);
        List<Student> studentList = mapper.getStudentList2();
        studentList.forEach(System.out::println);
        // 关闭session
        sqlSession.close();
    }

    @Test
    public void demo3(){
        // 获取sqlsession对象
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        // 执行sql
        StudentDao mapper = sqlSession.getMapper(StudentDao.class);
        Teacher teacher = mapper.getTeacherById(1);
        System.out.println(teacher.toString());
        // 关闭session
        sqlSession.close();
    }

    @Test
    public void demo4(){
        // 获取sqlsession对象
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        // 执行sql
        StudentDao mapper = sqlSession.getMapper(StudentDao.class);
        Teacher teacher = mapper.getTeacherById2(1);
        System.out.println(teacher.toString());
        // 关闭session
        sqlSession.close();
    }
}
