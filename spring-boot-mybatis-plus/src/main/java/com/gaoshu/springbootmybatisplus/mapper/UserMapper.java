package com.gaoshu.springbootmybatisplus.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.gaoshu.springbootmybatisplus.PO.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author gaoshu
 * @since 2021-11-25
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

}
