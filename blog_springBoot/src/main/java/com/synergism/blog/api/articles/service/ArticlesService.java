package com.synergism.blog.api.articles.service;

import com.synergism.blog.core.article.entity.ArticleInformation;
import com.synergism.blog.api.articles.entity.Pagination;
import com.synergism.blog.api.articles.enumeration.OrderBy;
import com.synergism.blog.result.entity.Result;

import java.util.List;

public interface ArticlesService {
    /**
     * 伙取分页
     * @param currentPage 当前页
     * @param pageSize 页容量
     * @param orderBy 排序
     * @return 分页
     */
    Result<Pagination> getPagination(int currentPage, int pageSize, OrderBy orderBy);
}
