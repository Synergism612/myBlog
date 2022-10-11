package com.synergism.blog.api.index.serviceImpl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.synergism.blog.api.index.entity.ArticleInformation;
import com.synergism.blog.api.index.entity.Pagination;
import com.synergism.blog.api.index.service.IndexService;
import com.synergism.blog.core.article.entity.Article;
import com.synergism.blog.core.article.service.ArticleService;
import com.synergism.blog.core.article_comment.entity.ArticleComment;
import com.synergism.blog.core.article_comment.service.ArticleCommentService;
import com.synergism.blog.core.user.entity.User;
import com.synergism.blog.core.user.service.UserService;
import com.synergism.blog.core.user_article.entity.UserArticle;
import com.synergism.blog.core.user_article.service.UserArticleService;
import com.synergism.blog.result.entity.Result;
import com.synergism.blog.utils.TypeUtil;
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
    private final ArticleCommentService articleCommentService;

    @Autowired
    public IndexServiceImpl(UserService userService, UserArticleService userArticleService, ArticleService articleService, ArticleCommentService articleCommentService) {
        this.userService = userService;
        this.userArticleService = userArticleService;
        this.articleService = articleService;
        this.articleCommentService = articleCommentService;
    }

    @Override
    public Result<Pagination> article(int currentPage, int pageSize) {
        //查询公有的文章
        List<UserArticle> userArticleList = userArticleService.list(new QueryWrapper<UserArticle>().eq("if_private", "0"));

        List<Article> articleList = articleService
                .listByIds(userArticleList.stream()
                        .map(UserArticle::getArticleId)
                        .collect(Collectors.toList()));

        List<String> userNameList = userService
                .listByIds(userArticleList.stream()
                        .map(UserArticle::getUserId)
                        .collect(Collectors.toList()))
                .stream()
                .map(User::getName)
                .collect(Collectors.toList());

        List<Long> commentCountList = userArticleList.stream()
                .map(U -> articleCommentService
                        .count(new QueryWrapper<ArticleComment>()
                                .eq("article_id", U.getArticleId())))
                .collect(Collectors.toList());

        //娶不到对象
        TypeUtil.isNull(articleList, userNameList);

        //获取对应数据
        List<ArticleInformation> articleInformationList = new ArrayList<>();
        for (int i = 0; i < userArticleList.size(); i++) {
            articleInformationList.add(new ArticleInformation(articleList.get(i), userNameList.get(i), userArticleList.get(i).getIfPrivate(), commentCountList.get(i)));
        }

        //封装结果
        return Result.success(new Pagination(articleInformationList, currentPage, pageSize, articleInformationList.size()));
    }
}
