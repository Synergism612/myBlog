package com.synergism.blog.api.articleAPI.service;

import com.synergism.blog.api.articleAPI.entity.Pagination;
import com.synergism.blog.api.articleAPI.enumeration.OrderBy;
import com.synergism.blog.result.Result;

public interface ArticleAPIService {
    /**
     * 伙取分页
     * @param currentPage 当前页
     * @param pageSize 页容量
     * @param orderBy 排序
     * @return 分页
     */
    Result<Pagination> getPagination(int currentPage, int pageSize, OrderBy orderBy);
}
