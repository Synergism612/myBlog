package com.synergism.blog.api.index.service;

import com.synergism.blog.api.articles.entity.Pagination;
import com.synergism.blog.api.comments.entity.Comments;
import com.synergism.blog.api.user.entity.UserInformation;
import com.synergism.blog.result.entity.Result;

import java.util.List;

public interface IndexService {
    Result<Pagination> getArticles(int currentPage, int pageSize);

    Result<UserInformation> getUserInfo();

    Result<List<Comments>> getComments();
}
