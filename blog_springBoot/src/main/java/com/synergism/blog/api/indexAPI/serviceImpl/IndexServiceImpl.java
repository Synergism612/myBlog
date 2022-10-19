package com.synergism.blog.api.indexAPI.serviceImpl;

import com.synergism.blog.api.articleAPI.entity.Pagination;
import com.synergism.blog.core.article.enumeration.ArticleSort;
import com.synergism.blog.api.articleAPI.service.ArticleAPIService;
import com.synergism.blog.api.indexAPI.service.IndexService;
import com.synergism.blog.core.user.entity.UserInformation;
import com.synergism.blog.core.user.service.UserService;
import com.synergism.blog.result.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IndexServiceImpl implements IndexService {

    private final ArticleAPIService articleAPIService;
    private final UserService userService;

    @Autowired
    public IndexServiceImpl(ArticleAPIService articleAPIService, UserService userService) {
        this.articleAPIService = articleAPIService;
        this.userService = userService;
    }

    @Override
    public Result<Pagination> getArticles(int currentPage, int pageSize, ArticleSort articleSort) {
        return articleAPIService.getPagination(currentPage, pageSize, articleSort);
    }

    @Override
    public Result<UserInformation> getUserInfo() {
        return Result.success(UserInformation.getInstance(userService.getById(1)));
    }
}
