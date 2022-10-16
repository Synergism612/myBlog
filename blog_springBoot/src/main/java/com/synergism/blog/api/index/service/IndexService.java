package com.synergism.blog.api.index.service;

import com.synergism.blog.api.articles.entity.Pagination;
import com.synergism.blog.api.articles.enumeration.OrderBy;
import com.synergism.blog.api.comments.entity.Comments;
import com.synergism.blog.api.user.entity.UserInformation;
import com.synergism.blog.result.entity.Result;

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

    /**
     * 首页最热评论信息
     * @return 结果[评论列表]
     */
    Result<List<Comments>> getComments();
}
