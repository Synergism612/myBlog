package com.synergism.blog.api.contentAPI.serviceImpl;

import com.synergism.blog.api.contentAPI.entity.Content;
import com.synergism.blog.api.contentAPI.service.ContentAPIService;
import com.synergism.blog.core.article.entity.Article;
import com.synergism.blog.core.article.service.ArticleService;
import com.synergism.blog.core.classify.entity.Classify;
import com.synergism.blog.core.classify.service.ClassifyService;
import com.synergism.blog.core.comment.entity.CommentParent;
import com.synergism.blog.core.comment.service.CommentService;
import com.synergism.blog.core.tag.entity.Tag;
import com.synergism.blog.core.tag.service.TagService;
import com.synergism.blog.core.user.entity.Author;
import com.synergism.blog.core.user.service.UserService;
import com.synergism.blog.result.CodeMsg;
import com.synergism.blog.result.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ContentAPIServiceImpl implements ContentAPIService {

    private final ArticleService articleService;
    private final UserService userService;
    private final ClassifyService classifyService;
    private final TagService tagService;
    private final CommentService commentService;

    @Autowired
    public ContentAPIServiceImpl(ArticleService articleService, UserService userService, ClassifyService classifyService, TagService tagService, CommentService commentService) {
        this.articleService = articleService;
        this.userService = userService;
        this.classifyService = classifyService;
        this.tagService = tagService;
        this.commentService = commentService;
    }

    @Override
    public Result<Article> getArticle(long id) {
        return Result.success(articleService.getById(id));
    }

    @Override
    public Result<Author> getAuthor(long id) {
        return Result.success(userService.getAuthorByArticleID(id));
    }

    @Override
    public Result<Classify> getClassify(long id) {
        return Result.success(classifyService.getOneByArticleID(id));
    }

    @Override
    public Result<List<Tag>> getTagList(long id) {
        return Result.success(tagService.getListByArticleID(id));
    }

    @Override
    public Result<List<CommentParent>> getCommentList(long id) {
        List<CommentParent> result = commentService.getAllListByArticleID(id);
        if (result.size() > 2) {
            result = result
                    .stream()
                    //按点赞数倒序排序
                    .sorted(Comparator.comparing(CommentParent::getLikeCount).reversed())
                    //获得前两个
                    .limit(2)
                    .collect(Collectors.toList());
        }
        return Result.success(result);
    }
}
