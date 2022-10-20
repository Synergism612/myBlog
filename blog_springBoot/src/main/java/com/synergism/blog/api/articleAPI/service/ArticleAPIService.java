package com.synergism.blog.api.articleAPI.service;

import com.synergism.blog.api.articleAPI.entity.Pagination;
import com.synergism.blog.core.article.enumeration.ArticleSort;
import com.synergism.blog.result.Result;

public interface ArticleAPIService {
    /**
     * 伙取分页
     *
     * @param currentPage 当前页
     * @param pageSize    页容量
     * @param articleSort 排序
     * @return 分页
     */
    Result<Pagination> getPagination(int currentPage, int pageSize, ArticleSort articleSort);
}
