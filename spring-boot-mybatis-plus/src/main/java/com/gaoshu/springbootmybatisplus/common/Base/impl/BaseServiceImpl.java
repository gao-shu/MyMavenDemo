package com.gaoshu.springbootmybatisplus.common.Base.impl;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gaoshu.springbootmybatisplus.common.Base.BaseService;

/**
 * @Title: BaseServiceImpl
 * @Description:
 * @author: gaoshu
 * @date: 2021/12/22 19:48
 */
public class BaseServiceImpl<M extends BaseMapper<T>, T> extends ServiceImpl<M, T> implements BaseService<T>{
}
