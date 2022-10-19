package com.synergism.blog.api.indexAPI.service;

import com.synergism.blog.api.articleAPI.entity.Pagination;
import com.synergism.blog.core.article.enumeration.ArticleSort;
import com.synergism.blog.core.user.entity.UserInformation;
import com.synergism.blog.result.Result;

public interface IndexService {
    /**
     * 首页文章列表
     * @param currentPage 当前页
     * @param pageSize 页容量
     * @param articleSort 排序
     * @return 结果[分页]
     */
    Result<Pagination> getArticles(int currentPage, int pageSize, ArticleSort articleSort);

    /**
     * 首页默认用户信息
     * @return 结果[用户信息]
     */
    Result<UserInformation> getUserInfo();
}
