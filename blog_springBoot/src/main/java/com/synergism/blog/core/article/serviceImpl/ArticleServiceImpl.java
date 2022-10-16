package com.synergism.blog.core.article.serviceImpl;

import com.synergism.blog.core.article.entity.Article;
import com.synergism.blog.core.article.entity.ArticleInformation;
import com.synergism.blog.core.article.mapper.ArticleMapper;
import com.synergism.blog.core.article.service.ArticleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.synergism.blog.core.article_classify.service.ArticleClassifyService;
import com.synergism.blog.core.article_comment.service.ArticleCommentService;
import com.synergism.blog.core.article_tag.service.ArticleTagService;
import com.synergism.blog.core.classify.entity.Classify;
import com.synergism.blog.core.comment.entity.CommentInformation;
import com.synergism.blog.core.tag.entity.Tag;
import com.synergism.blog.core.user.entity.User;
import com.synergism.blog.core.user.service.UserService;
import com.synergism.blog.core.user_article.entity.UserArticle;
import com.synergism.blog.core.user_article.service.UserArticleService;
import com.synergism.blog.utils.TypeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 文章表 服务实现类
 * </p>
 *
 * @author Synergism
 * @since 2022-10-07 10:07:37
 */
@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article> implements ArticleService {


    private final UserService userService;
    private final UserArticleService userArticleService;
    private final ArticleCommentService articleCommentService;
    private final ArticleClassifyService articleClassifyService;
    private final ArticleTagService articleTagService;

    @Autowired
    public ArticleServiceImpl(UserService userService, UserArticleService userArticleService, ArticleCommentService articleCommentService, ArticleClassifyService articleClassifyService, ArticleTagService articleTagService) {
        this.userService = userService;
        this.userArticleService = userArticleService;
        this.articleCommentService = articleCommentService;
        this.articleClassifyService = articleClassifyService;
        this.articleTagService = articleTagService;
    }

    @Override
    public List<ArticleInformation> getAllArticleInformation() {
        //查询用户文章对照表
        List<UserArticle> userArticleList = userArticleService.list();
        //文章id列表
        List<Long> articleIDList = userArticleList.stream()
                .map(UserArticle::getArticleId).collect(Collectors.toList());
        //用户id列表
        List<Long> userIDList = userArticleList.stream()
                .map(UserArticle::getUserId).collect(Collectors.toList());

        //查询文章列表
        List<Article> articleList = this
                .listByIds(articleIDList);

        //查询用户列表
        List<String> userNameList = userService
                .listByIds(userIDList)
                .stream()
                .map(User::getName)
                .collect(Collectors.toList());

        //查询评论列表
        List<List<CommentInformation>> commentInformationList = articleCommentService
                .getCommentInformationListByArticleIDList(articleIDList);

        //评论数列表
        List<Integer> commentCountList = commentInformationList.stream().map(List::size).collect(Collectors.toList());

        //查询文章分类
        List<List<Classify>> classifyList = articleClassifyService
                .getClassifyListByArticleIDList(articleIDList);

        //查询文章标签
        List<List<Tag>> tagList = articleTagService
                .getTagListByArticleIDList(articleIDList);

        //娶不到对象判空
        TypeUtil.isNull(articleList, userNameList, classifyList, tagList);

        //封装所需数据
        List<ArticleInformation> articleInformationList = new ArrayList<>();
        for (int i = 0; i < userArticleList.size(); i++) {
            articleInformationList.add(
                    new ArticleInformation(articleList.get(i),
                            userNameList.get(i),
                            userArticleList.get(i).getIfPrivate(),
                            commentCountList.get(i),
                            commentInformationList.get(i),
                            classifyList.get(i),
                            tagList.get(i)));
        }

        //结果封装
        return articleInformationList;
    }
}
