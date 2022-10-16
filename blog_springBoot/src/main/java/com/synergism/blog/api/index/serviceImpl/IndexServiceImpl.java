package com.synergism.blog.api.index.serviceImpl;

import com.synergism.blog.api.articles.entity.Pagination;
import com.synergism.blog.api.articles.enumeration.OrderBy;
import com.synergism.blog.api.articles.service.ArticlesService;
import com.synergism.blog.api.comments.entity.Comments;
import com.synergism.blog.api.comments.service.CommentsService;
import com.synergism.blog.api.index.service.IndexService;
import com.synergism.blog.api.user.entity.UserInformation;
import com.synergism.blog.core.user.service.UserService;
import com.synergism.blog.result.entity.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IndexServiceImpl implements IndexService {

    private final ArticlesService articlesService;
    private final UserService userService;
    private final CommentsService commentsService;

    @Autowired
    public IndexServiceImpl(ArticlesService articlesService, UserService userService, CommentsService commentsService) {
        this.articlesService = articlesService;
        this.userService = userService;
        this.commentsService = commentsService;
    }

    @Override
    public Result<Pagination> getArticles(int currentPage, int pageSize, OrderBy orderBy) {
        return Result.success(articlesService.getPagination(currentPage, pageSize, orderBy));
    }

    @Override
    public Result<UserInformation> getUserInfo() {
        return Result.success(UserInformation.getInstance(userService.getById(1)));
    }

    @Override
    public Result<List<Comments>> getComments() {
        return Result.success(commentsService.getIndexComments());
    }
}
