package com.synergism.blog.api.contentAPI.serviceImpl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.synergism.blog.api.contentAPI.entity.AddComment;
import com.synergism.blog.api.contentAPI.service.ContentAPIService;
import com.synergism.blog.core.article.entity.Article;
import com.synergism.blog.core.article.entity.ArticleTagNominate;
import com.synergism.blog.core.article.service.ArticleService;
import com.synergism.blog.core.classify.entity.Classify;
import com.synergism.blog.core.classify.service.ClassifyService;
import com.synergism.blog.core.comment.entity.CommentParent;
import com.synergism.blog.core.comment.service.CommentService;
import com.synergism.blog.core.tag.entity.Tag;
import com.synergism.blog.core.tag.service.TagService;
import com.synergism.blog.core.user.entity.Author;
import com.synergism.blog.core.user.entity.User;
import com.synergism.blog.core.user.service.UserService;
import com.synergism.blog.result.CodeMsg;
import com.synergism.blog.result.Result;
import com.synergism.blog.utils.StringUtil;
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
    public Result<Article> getArticle(long articleID) {
        return Result.success(articleService.getById(articleID));
    }

    @Override
    public Result<Author> getAuthor(long articleID) {
        return Result.success(userService.getAuthorByArticleID(articleID));
    }

    @Override
    public Result<Classify> getClassify(long articleID) {
        return Result.success(classifyService.getOneByArticleID(articleID));
    }

    @Override
    public Result<List<Tag>> getTagList(long articleID) {
        return Result.success(tagService.getListByArticleID(articleID));
    }

    @Override
    public Result<List<CommentParent>> getCommentList(long articleID) {
        return Result.success(commentService.getAllListByArticleID(articleID));
    }

    @Override
    public Result<List<Article>> getClassifyNominate(long articleID) {
        return Result.success(articleService.getOneClassifyArticleList(articleID));
    }

    @Override
    public Result<List<ArticleTagNominate>> getTagNominate(long articleID) {
        return Result.success(articleService.getMoreTagArticleList(articleID));
    }

    @Override
    public Result<String> saveComment(AddComment addComment) {
        if (StringUtil.isEmpty(addComment.getComment())){
            return Result.error(CodeMsg.BIND_ERROR.fillArgs("评论不能为空"));
        }
        long userID = userService.getID(addComment.getUsername());
        if (userID==-1){
            return Result.error(CodeMsg.BIND_ERROR.fillArgs("找不到用户"));
        }
        if (!articleService.isExist(addComment.getArticleID())) {
            return Result.error(CodeMsg.BIND_ERROR.fillArgs("找不到文章"));
        }
        if (addComment.getRootID() == null || commentService.isExist(addComment.getRootID())) {
            if (addComment.getParentID() == null || commentService.isExist(addComment.getParentID())) {
                return commentService.save(addComment.getComment(),addComment.getRootID(),addComment.getParentID(), addComment.getArticleID(),userID)
                        ?Result.success()
                        :Result.error(CodeMsg.BIND_ERROR.fillArgs("评论失败"));
            }
        }
        return Result.error(CodeMsg.BIND_ERROR.fillArgs("评论失败"));
    }
}
