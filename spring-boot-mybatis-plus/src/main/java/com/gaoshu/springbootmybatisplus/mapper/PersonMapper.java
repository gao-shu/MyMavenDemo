package com.gaoshu.springbootmybatisplus.mapper;

import com.gaoshu.springbootmybatisplus.PO.Person;
import com.gaoshu.springbootmybatisplus.common.Base.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author gaoshu
 * @since 2021-12-22
 */
@Mapper
public interface PersonMapper extends BaseMapper<Person> {

}
