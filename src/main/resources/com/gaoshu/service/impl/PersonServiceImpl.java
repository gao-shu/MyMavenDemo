package com.gaoshu.service.impl;

import com.gaoshu.entity.PO.Person;
import com.gaoshu.mapper.PersonMapper;
import com.gaoshu.service.IPersonService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
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
