package com.gaoshu.dao;

import com.gaoshu.pojo.Student;
import com.gaoshu.pojo.Teacher;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface StudentDao {
    @Select("select * from student where student_id = #{id}")
    Student getStudentById(Integer id);

    /*按照查询嵌套处理*/
    List<Student> getStudentList();
    /*按照结果嵌套处理*/
    List<Student> getStudentList2();

    /*按照查询嵌套处理*/
    Teacher getTeacherById(Integer id);
    /*按照结果嵌套处理*/
    Teacher getTeacherById2(Integer id);

    //
}
