package com.synergism.blog.api.contentAPI.controller;


import com.synergism.blog.api.contentAPI.entity.CommentForm;
import com.synergism.blog.api.contentAPI.service.ContentAPIService;
import com.synergism.blog.core.article.entity.Article;
import com.synergism.blog.core.article.entity.ArticleTagNominate;
import com.synergism.blog.core.classify.entity.Classify;
import com.synergism.blog.core.comment.entity.CommentParent;
import com.synergism.blog.core.tag.entity.Tag;
import com.synergism.blog.core.user.entity.Author;
import com.synergism.blog.result.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * 内容页接口
 */
@RestController
@RequestMapping("/api/blog/content")
public class ContentAPIController {

    private final ContentAPIService service;

    @Autowired
    public ContentAPIController(ContentAPIService service) {
        this.service = service;
    }

    /**
     * 内容页文章信息获取接口
     *
     * @param articleID 文章id
     * @return 文章信息
     */
    @Validated
    @GetMapping("article")
    public Result<Article> article(
            @RequestParam @NotNull(message = "文章不存在")
            @Min(value = 1, message = "文章不存在") Long articleID
    ) {
        return service.getArticle(articleID);
    }

    /**
     * 内容页作者信息获取接口
     *
     * @param articleID 文章id
     * @return 作者信息
     */
    @Validated
    @GetMapping("author")
    public Result<Author> author(
            @RequestParam @NotNull(message = "文章不存在")
            @Min(value = 1, message = "文章不存在") Long articleID
    ) {
        return service.getAuthor(articleID);
    }

    /**
     * 内容页分类信息获取接口
     *
     * @param articleID 文章id
     * @return 分类信息
     */
    @Validated
    @GetMapping("classify")
    public Result<Classify> classify(
            @RequestParam @NotNull(message = "文章不存在")
            @Min(value = 1, message = "文章不存在") Long articleID
    ) {
        return service.getClassify(articleID);
    }

    /**
     * 内容页标签信息获取接口
     *
     * @param articleID 文章id
     * @return 标签信息
     */
    @Validated
    @GetMapping("tag")
    public Result<List<Tag>> tag(
            @RequestParam @NotNull(message = "文章不存在")
            @Min(value = 1, message = "文章不存在") Long articleID
    ) {
        return service.getTag(articleID);
    }

    /**
     * 内容页评论获取接口
     *
     * @param articleID 文章id
     * @return 评论信息列表
     */
    @Validated
    @GetMapping("comment")
    public Result<List<CommentParent>> comment(
            @RequestParam @NotNull(message = "文章不存在")
            @Min(value = 1, message = "文章不存在") Long articleID
    ) {
        return service.getComment(articleID);
    }

    /**
     * 内容页同分类推荐获取接口
     *
     * @param articleID 文章id
     * @return 文章信息列表
     */
    @Validated
    @GetMapping("nominate/classify")
    public Result<List<Article>> classifyNominate(
            @RequestParam @NotNull(message = "文章不存在")
            @Min(value = 1, message = "文章不存在") Long articleID
    ) {
        return service.getClassifyNominate(articleID);
    }

    /**
     * 内容页同标签推荐获取接口
     *
     * @param articleID 文章id
     * @return 文章信息列表
     */
    @Validated
    @GetMapping("nominate/tag")
    public Result<List<ArticleTagNominate>> tagNominate(
            @RequestParam @NotNull(message = "文章不存在")
            @Min(value = 1, message = "文章不存在") Long articleID
    ) {
        return service.getTagNominate(articleID);
    }

    /**
     * 内容页新增评论接口
     *
     * @param commentForm 评论信息
     * @return 成功
     */
    //需要登录验证
    @Validated
    @PostMapping("comment")
    public Result<String> saveComment(@RequestBody @Valid CommentForm commentForm) {
        if (commentForm.getRootID() == -1) commentForm.setRootID(null);
        if (commentForm.getParentID() == -1) commentForm.setParentID(null);
        return service.saveComment(commentForm);
    }

    /**
     * 内容页文章点过赞
     *
     * @param articleID 文章id
     * @return 点过为真，反之为假
     */
    //需要登录验证
    @Validated
    @GetMapping("article/like")
    public Result<Boolean> getArticleLike(
            @RequestParam @NotEmpty(message = "用户不存在") String username,
            @RequestParam @NotNull(message = "文章id不能为空")
            @Min(value = 1, message = "文章不存在") Long articleID
    ) {
        return service.getArticleLike(username, articleID);
    }

    /**
     * 内容页文章点赞
     *
     * @param username 账号
     * @param articleID 文章id
     * @return 成功
     */
    //需要登录验证
    @Validated
    @PatchMapping("article/like")
    public Result<String> updateArticleLike(
            @RequestParam @NotEmpty(message = "用户不存在") String username,
            @RequestParam @NotNull(message = "文章不存在")
            @Min(value = 1, message = "文章不存在") Long articleID,
            @RequestParam @NotNull(message = "状态码不能为空") Boolean state
    ) {
        return service.updateArticleLike(username, articleID, state);
    }


    /**
     * 内容页点过赞评论
     * @param username 账号
     * @return 评论id列表
     */
    //需要登录验证
    @Validated
    @GetMapping("comment/like")
    public Result<List<Long>> getCommentLike(
            @RequestParam @NotEmpty(message = "用户不存在") String username
    ) {
        return service.getCommentLike(username);
    }

    /**
     * 内容页评论点赞
     *
     * @param username 账号
     * @param commentID 评论id
     * @return 成功
     */
    //需要登录验证
    @Validated
    @PatchMapping("comment/like")
    public Result<String> updateCommentLike(
            @RequestParam @NotEmpty(message = "用户不存在") String username,
            @RequestParam @NotNull(message = "评论不存在")
            @Min(value = 1, message = "评论不存在") Long commentID,
            @RequestParam @NotNull(message = "状态码不能为空") Boolean state
    ) {
        return service.updateCommentLike(username, commentID, state);
    }
}
