package com.synergism.blog.api.index.serviceImpl;

import com.synergism.blog.api.articles.entity.Pagination;
import com.synergism.blog.api.articles.enumeration.OrderBy;
import com.synergism.blog.api.articles.service.ArticlesService;
import com.synergism.blog.core.comment.entity.CommentInformation;
import com.synergism.blog.api.index.service.IndexService;
import com.synergism.blog.api.user.entity.UserInformation;
import com.synergism.blog.core.comment.service.CommentService;
import com.synergism.blog.core.user.service.UserService;
import com.synergism.blog.result.entity.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class IndexServiceImpl implements IndexService {

    private final ArticlesService articlesService;
    private final UserService userService;
    private final CommentService commentService;

    @Autowired
    public IndexServiceImpl(ArticlesService articlesService, UserService userService, CommentService commentService) {
        this.articlesService = articlesService;
        this.userService = userService;
        this.commentService = commentService;
    }

    @Override
    public Result<Pagination> getArticles(int currentPage, int pageSize, OrderBy orderBy) {
        return articlesService.getPagination(currentPage, pageSize, orderBy);
    }

    @Override
    public Result<UserInformation> getUserInfo() {
        return Result.success(UserInformation.getInstance(userService.getById(1)));
    }

    @Override
    public Result<List<CommentInformation>> getComments() {
        List<CommentInformation> result = commentService.getAllComment()
                .stream()
                .sorted(Comparator.comparing(CommentInformation::getLikeCount).reversed())
                .collect(Collectors.toList());
        if (result.size() > 5)
            result = result.subList(0, 5);
        return Result.success(result);
    }
}
