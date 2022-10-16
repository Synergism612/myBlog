package com.synergism.blog.api.articles.service;

import com.synergism.blog.api.articles.entity.ArticleInformation;
import com.synergism.blog.api.articles.entity.Pagination;
import com.synergism.blog.api.articles.enumeration.OrderBy;

import java.util.List;

public interface ArticlesService {
    /**
     * 获取所有文章信息
     * @return 文章信息列表
     */
    List<ArticleInformation> getAllArticleInformation();

    /**
     * 伙取分页
     * @param currentPage 当前页
     * @param pageSize 页容量
     * @param orderBy 排序
     * @return 分页
     */
    Pagination getPagination(int currentPage, int pageSize, OrderBy orderBy);
}
