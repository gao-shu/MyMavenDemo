package com.gaoshu.dao;

import cn.hutool.core.lang.Dict;
import cn.hutool.json.JSONObject;
import com.gaoshu.pojo.User;
import com.gaoshu.pojo.VO.UserVO;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

public interface UserDao {

    List<User> getUserList();

    User getUserById(Integer id);

    @Select("select * from user where id = #{id}")
    JSONObject getUserById2(Integer id);

    @Select("select id, name , pwd as password from user where id = #{id}")
    UserVO getUserById3(Integer id);

    User getUserById4(Integer id);

    UserVO getUserById5(Integer id);

    List<User> getUserBylimit(Map<String, Integer> map);
    List<User> getUserBylimit(Dict dict);
    // 分页
    List<User> getUserByRoeBounds(Dict dict);
    //注解
//    UserVO getUserById6(@Param("userId") Integer id);
    UserVO getUserById6(@Param("id") Integer id);

}
