package com.gaoshu.service.impl;

import com.gaoshu.entity.PO.Category;
import com.gaoshu.mapper.CategoryMapper;
import com.gaoshu.service.ICategoryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author gaoshu
 * @since 2021-12-02
 */
@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements ICategoryService {

}
