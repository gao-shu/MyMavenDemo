package com.gaoshu.service.impl;

import com.gaoshu.entity.PO.Article;
import com.gaoshu.mapper.ArticleMapper;
import com.gaoshu.service.IArticleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 文章 服务实现类
 * </p>
 *
 * @author gaoshu
 * @since 2021-12-02
 */
@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article> implements IArticleService {

}
