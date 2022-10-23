package com.synergism.blog.api.contentAPI.serviceImpl;

import com.synergism.blog.api.contentAPI.entity.Content;
import com.synergism.blog.api.contentAPI.service.ContentAPIService;
import com.synergism.blog.core.article.entity.Article;
import com.synergism.blog.core.article.service.ArticleService;
import com.synergism.blog.core.classify.entity.Classify;
import com.synergism.blog.core.classify.service.ClassifyService;
import com.synergism.blog.core.comment.entity.CommentInformation;
import com.synergism.blog.core.comment.service.CommentService;
import com.synergism.blog.core.tag.entity.Tag;
import com.synergism.blog.core.tag.service.TagService;
import com.synergism.blog.core.user.entity.UserInformation;
import com.synergism.blog.core.user.service.UserService;
import com.synergism.blog.result.CodeMsg;
import com.synergism.blog.result.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
    public Result<Content> getContent(long id, String title) {
        Article article = articleService.getById(id);
        if (!title.equals(article.getTitle())) {
            return Result.error(CodeMsg.BIND_ERROR.fillArgs("参数异常"));
        }
        UserInformation userInformation = userService.getOneByArticleID(id);

        Classify classify = classifyService.getOneByArticleID(id);

        List<Tag> tagList = tagService.getListByArticleID(id);

        List<CommentInformation> commentInformationList = commentService.getTopListByArticleID(id);

        return Result.success(new Content(article, userInformation, classify, tagList, commentInformationList));
    }
}
