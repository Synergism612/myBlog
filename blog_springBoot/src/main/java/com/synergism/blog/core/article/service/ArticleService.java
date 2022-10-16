package com.synergism.blog.core.article.service;

import com.synergism.blog.core.article.entity.Article;
import com.baomidou.mybatisplus.extension.service.IService;
import com.synergism.blog.core.article.entity.ArticleInformation;

import java.util.List;


/**
 * <p>
 * 文章表 服务类
 * </p>
 *
 * @author Synergism
 * @since 2022-10-07 10:07:37
 */
public interface ArticleService extends IService<Article> {
    /**
     * 获取所有文章信息
     * @return 文章信息列表
     */
    List<ArticleInformation> getAllArticleInformation();

}
