package com.synergism.blog.core.article.service;

import com.synergism.blog.core.article.entity.Article;
import com.baomidou.mybatisplus.extension.service.IService;

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
     * 获取文章信息列表
     * @param currentPage 当前页数
     * @param pageSize 当前页内容数
     * @return 结果[List<Article>]
     */
    List<Article> pagination(int currentPage, int pageSize);
}
