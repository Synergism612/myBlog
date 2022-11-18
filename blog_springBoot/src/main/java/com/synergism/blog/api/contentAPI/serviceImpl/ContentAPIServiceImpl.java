package com.synergism.blog.api.contentAPI.serviceImpl;

import com.synergism.blog.api.contentAPI.entity.CommentForm;
import com.synergism.blog.api.contentAPI.service.ContentAPIService;
import com.synergism.blog.core.article.entity.Article;
import com.synergism.blog.core.article.entity.ArticleTagNominate;
import com.synergism.blog.core.article.service.ArticleService;
import com.synergism.blog.core.classify.entity.Classify;
import com.synergism.blog.core.classify.service.ClassifyService;
import com.synergism.blog.core.comment.entity.CommentParent;
import com.synergism.blog.core.comment.service.CommentService;
import com.synergism.blog.core.like.service.LikeService;
import com.synergism.blog.core.tag.entity.Tag;
import com.synergism.blog.core.tag.service.TagService;
import com.synergism.blog.core.user.entity.Author;
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
    private final LikeService likeService;

    @Autowired
    public ContentAPIServiceImpl(ArticleService articleService, UserService userService, ClassifyService classifyService, TagService tagService, CommentService commentService, LikeService likeService) {
        this.articleService = articleService;
        this.userService = userService;
        this.classifyService = classifyService;
        this.tagService = tagService;
        this.commentService = commentService;
        this.likeService = likeService;
    }

    @Override
    public Result<Article> getArticle(long articleID) {
        articleService.updateViews(articleID,1);
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
    public Result<List<Tag>> getTag(long articleID) {
        return Result.success(tagService.getListByArticleID(articleID));
    }

    @Override
    public Result<List<CommentParent>> getComment(long articleID) {
        return Result.success(commentService.getCommentParentListByArticleID(articleID));
    }

    @Override
    public Result<List<Article>> getClassifyNominate(long articleID) {
        return Result.success(articleService.getSameClassifyArticleList(articleID));
    }

    @Override
    public Result<List<ArticleTagNominate>> getTagNominate(long articleID) {
        return Result.success(articleService.getMoreTagArticleList(articleID));
    }

    @Override
    public Result<String> saveComment(CommentForm commentForm) {
        long userID = userService.getID(commentForm.getUsername());
        if (userID == -1) {
            return Result.error(CodeMsg.BIND_ERROR.fillArgs("用户不存在"));
        }
        if (!articleService.isExist(commentForm.getArticleID())) {
            return Result.error(CodeMsg.BIND_ERROR.fillArgs("文章不存在"));
        }
        if (commentForm.getRootID() != null && !commentService.isExist(commentForm.getRootID())) {
            return Result.error(CodeMsg.BIND_ERROR.fillArgs("根评论不存在"));
        }
        if (commentForm.getParentID() != null && !commentService.isExist(commentForm.getParentID())) {
            return Result.error(CodeMsg.BIND_ERROR.fillArgs("父评论不存在"));
        }
        commentService.save(commentForm.getComment(), commentForm.getRootID(), commentForm.getParentID(), commentForm.getArticleID(), userID);
        return Result.success();
    }

    @Override
    public Result<String> updateArticleLike(String username,long articleID,boolean state) {
        if (!userService.isExist(username)){
            return Result.error(CodeMsg.BIND_ERROR.fillArgs("用户不存在"));
        }
        if (!articleService.isExist(articleID)){
            return Result.error(CodeMsg.BIND_ERROR.fillArgs("文章不存在"));
        }
        likeService.likeArticle(username,articleID,state);
        return Result.success();
    }

    @Override
    public Result<Boolean> getArticleLike(String username, long articleID) {
        if (!userService.isExist(username)){
            return Result.error(CodeMsg.BIND_ERROR.fillArgs("用户不存在"));
        }
        if (!articleService.isExist(articleID)){
            return Result.error(CodeMsg.BIND_ERROR.fillArgs("文章不存在"));
        }
        return Result.success(likeService.isLikeArticle(username,articleID));
    }

    @Override
    public Result<String> updateCommentLike(String username, long commentID, boolean state) {
        if (!userService.isExist(username)){
            return Result.error(CodeMsg.BIND_ERROR.fillArgs("用户不存在"));
        }
        if (!commentService.isExist(commentID)){
            return Result.error(CodeMsg.BIND_ERROR.fillArgs("评论不存在"));
        }
        likeService.likeComment(username,commentID,state);
        return Result.success();
    }

    @Override
    public Result<List<Long>> getCommentLike(String username) {
        if (!userService.isExist(username)){
            return Result.error(CodeMsg.BIND_ERROR.fillArgs("用户不存在"));
        }
        return Result.success(likeService.getLikeCommentIDList(username));
    }
}
