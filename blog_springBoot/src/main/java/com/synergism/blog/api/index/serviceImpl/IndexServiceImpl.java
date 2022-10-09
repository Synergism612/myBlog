package com.synergism.blog.api.index.serviceImpl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.synergism.blog.api.index.entity.ArticleInformation;
import com.synergism.blog.api.index.entity.Pagination;
import com.synergism.blog.api.index.service.IndexService;
import com.synergism.blog.api.user.service.UserApiService;
import com.synergism.blog.core.article.entity.Article;
import com.synergism.blog.core.article.service.ArticleService;
import com.synergism.blog.core.user.entity.User;
import com.synergism.blog.core.user.service.UserService;
import com.synergism.blog.core.user_article.entity.UserArticle;
import com.synergism.blog.core.user_article.service.UserArticleService;
import com.synergism.blog.result.entity.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class IndexServiceImpl implements IndexService {

    private final UserService userService;
    private final UserArticleService userArticleService;
    private final ArticleService articleService;

    @Autowired
    public IndexServiceImpl(UserService userService, UserArticleService userArticleService, ArticleService articleService) {
        this.userService = userService;
        this.userArticleService = userArticleService;
        this.articleService = articleService;
    }

    @Override
    public Result<Pagination> article(int currentPage, int pageSize) {
        //查询公有的文章
        List<UserArticle> userArticleList = userArticleService.list(new QueryWrapper<UserArticle>().eq("if_private", "0"));

        List<Article> articleList = articleService.listByIds(userArticleList.stream().map(UserArticle::getArticleId).collect(Collectors.toList()));

        List<String> userNameList = userService.listByIds(userArticleList.stream().map(UserArticle::getUserId).collect(Collectors.toList())).stream().map(User::getName).collect(Collectors.toList());

        //获取对应数据
        List<ArticleInformation> articleInformationList = new ArrayList<>();
        for (int i = 0; i < userArticleList.size(); i++) {
            articleInformationList.add(new ArticleInformation(articleList.get(i),userNameList.get(i),userArticleList.get(i).getIfPrivate()));
        }

        //封装结果
        return Result.success(new Pagination(articleInformationList,currentPage,pageSize,articleService.count()));
    }
}
