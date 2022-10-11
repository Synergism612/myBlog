package com.synergism.blog.core.article_tag.serviceImpl;

import com.synergism.blog.core.article_tag.entity.ArticleTag;
import com.synergism.blog.core.article_tag.mapper.ArticleTagMapper;
import com.synergism.blog.core.article_tag.service.ArticleTagService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 文章-标签对照表：文章的标签 服务实现类
 * </p>
 *
 * @author Synergism
 * @since 2022-10-11 05:58:21
 */
@Service
public class ArticleTagServiceImpl extends ServiceImpl<ArticleTagMapper, ArticleTag> implements ArticleTagService {

}
