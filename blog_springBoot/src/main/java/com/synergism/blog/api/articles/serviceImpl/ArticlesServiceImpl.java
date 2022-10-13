package com.synergism.blog.api.articles.serviceImpl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.synergism.blog.api.articles.entity.ArticleInformation;
import com.synergism.blog.api.articles.entity.Pagination;
import com.synergism.blog.api.articles.service.ArticlesService;
import com.synergism.blog.core.article.entity.Article;
import com.synergism.blog.core.article.service.ArticleService;
import com.synergism.blog.core.article_classify.service.ArticleClassifyService;
import com.synergism.blog.core.article_comment.entity.ArticleComment;
import com.synergism.blog.core.article_comment.service.ArticleCommentService;
import com.synergism.blog.core.article_tag.service.ArticleTagService;
import com.synergism.blog.core.classify.entity.Classify;
import com.synergism.blog.core.classify.service.ClassifyService;
import com.synergism.blog.core.tag.entity.Tag;
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
public class ArticlesServiceImpl implements ArticlesService {

    private final UserService userService;
    private final UserArticleService userArticleService;
    private final ArticleService articleService;
    private final ArticleCommentService articleCommentService;
    private final ArticleClassifyService articleClassifyService;
    private final ArticleTagService articleTagService;

    @Autowired
    public ArticlesServiceImpl(UserService userService, UserArticleService userArticleService, ArticleService articleService, ArticleCommentService articleCommentService, ArticleClassifyService articleClassifyService, ClassifyService classifyService, ArticleTagService articleTagService) {
        this.userService = userService;
        this.userArticleService = userArticleService;
        this.articleService = articleService;
        this.articleCommentService = articleCommentService;
        this.articleClassifyService = articleClassifyService;
        this.articleTagService = articleTagService;
    }

    @Override
    public Pagination getIndexArticleInformation(int currentPage, int pageSize) {
        //查询用户文章对照表
        List<UserArticle> userArticleList = userArticleService
                .list(new QueryWrapper<UserArticle>().eq("if_private", "0"));
        //文章id列表
        List<Long> articleIDList = userArticleList.stream()
                .map(UserArticle::getArticleId).collect(Collectors.toList());
        //用户id列表
        List<Long> userIDList = userArticleList.stream()
                .map(UserArticle::getUserId).collect(Collectors.toList());

        //查询文章列表
        List<Article> articleList = articleService
                .listByIds(articleIDList);

        //查询用户列表
        List<String> userNameList = userService
                .listByIds(userIDList)
                .stream()
                .map(User::getName)
                .collect(Collectors.toList());

        //查询评论数列表
        List<Long> commentCountList = articleIDList.stream()
                .map(articleID -> articleCommentService
                        .count(new QueryWrapper<ArticleComment>()
                                .eq("article_id", articleID)))
                .collect(Collectors.toList());

        //查询文章分类
        List<List<Classify>> classifyList = articleClassifyService
                .getClassifyListByArticleIDList(articleIDList);

        //查询文章标签
        List<List<Tag>> tagList = articleTagService
                .getTagListByArticleIDList(articleIDList);

        //娶不到对象判空
        TypeUtil.isNull(articleList, userNameList);

        //封装所需数据
        List<ArticleInformation> articleInformationList = new ArrayList<>();
        for (int i = 0; i < userArticleList.size(); i++) {
            articleInformationList.add(
                    new ArticleInformation(articleList.get(i),
                            userNameList.get(i),
                            userArticleList.get(i).getIfPrivate(),
                            commentCountList.get(i),
                            classifyList.get(i),
                            tagList.get(i)));
        }

        //结果封装
        return new Pagination(articleInformationList, currentPage, pageSize, articleInformationList.size());

    }
}