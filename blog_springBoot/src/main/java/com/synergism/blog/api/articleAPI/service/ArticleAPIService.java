package com.synergism.blog.api.articleAPI.service;

import com.synergism.blog.core.article.entity.Pagination;
import com.synergism.blog.core.article.enumeration.ArticleSort;
import com.synergism.blog.result.Result;

import java.util.List;

public interface ArticleAPIService {
    /**
     * 获取分页
     * 搜索字段使用
     * @param currentPage 当前页
     * @param pageSize 页容量
     * @param articleSort 排序
     * @param username 用户名
     * @param keyword 关键字
     * @param classifyIDList 分类
     * @param tagIDList 标签
     * @return Result[分页]
     */
    Result<Pagination> getPagination(int currentPage, int pageSize, ArticleSort articleSort, String username, String keyword, List<Long> classifyIDList, List<Long> tagIDList);
}
