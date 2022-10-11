package com.synergism.blog.core.article_classify.serviceImpl;

import com.synergism.blog.core.article_classify.entity.ArticleClassify;
import com.synergism.blog.core.article_classify.mapper.ArticleClassifyMapper;
import com.synergism.blog.core.article_classify.service.ArticleClassifyService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 分类-文章对照表：文章的分类 服务实现类
 * </p>
 *
 * @author Synergism
 * @since 2022-10-11 05:57:44
 */
@Service
public class ArticleClassifyServiceImpl extends ServiceImpl<ArticleClassifyMapper, ArticleClassify> implements ArticleClassifyService {

}
