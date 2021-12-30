package com.gaoshu.springbootmybatisplus.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gaoshu.springbootmybatisplus.PO.Person;
import com.gaoshu.springbootmybatisplus.mapper.PersonMapper;
import com.gaoshu.springbootmybatisplus.service.IPersonService;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author gaoshu
 * @since 2021-12-22
 */
@Service
public class PersonServiceImpl extends ServiceImpl<PersonMapper, Person> implements IPersonService {

}
