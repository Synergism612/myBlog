package com.synergism.blog.api.indexAPI.service;

import com.synergism.blog.api.articleAPI.entity.Pagination;
import com.synergism.blog.api.articleAPI.enumeration.OrderBy;
import com.synergism.blog.core.comment.entity.CommentInformation;
import com.synergism.blog.api.userAPI.entity.UserInformation;
import com.synergism.blog.result.Result;

import java.util.List;

public interface IndexService {
    /**
     * 首页文章列表
     * @param currentPage 当前页
     * @param pageSize 页容量
     * @param orderBy 排序
     * @return 结果[分页]
     */
    Result<Pagination> getArticles(int currentPage, int pageSize, OrderBy orderBy);

    /**
     * 首页默认用户信息
     * @return 结果[用户信息]
     */
    Result<UserInformation> getUserInfo();
}
