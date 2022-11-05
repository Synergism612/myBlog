package com.synergism.blog.api.contentAPI.service;

import com.synergism.blog.api.contentAPI.entity.CommentForm;
import com.synergism.blog.core.article.entity.Article;
import com.synergism.blog.core.article.entity.ArticleTagNominate;
import com.synergism.blog.core.classify.entity.Classify;
import com.synergism.blog.core.comment.entity.CommentParent;
import com.synergism.blog.core.tag.entity.Tag;
import com.synergism.blog.core.user.entity.Author;
import com.synergism.blog.result.Result;

import java.util.List;

public interface ContentAPIService {
    /**
     * 获得文章内容
     * @param articleID 文章id
     * @return 文章内容
     */
    Result<Article> getArticle(long articleID);

    /**
     * 获得文章作者信息
     * @param articleID 文章id
     * @return 作者信息
     */
    Result<Author> getAuthor(long articleID);

    /**
     * 获得文章分类
     * @param articleID 文章id
     * @return 分类
     */
    Result<Classify> getClassify(long articleID);

    /**
     * 获得文章标签列表
     * @param articleID 文章id
     * @return 标签列表
     */
    Result<List<Tag>> getTagList(long articleID);

    /**
     * 获得文章下评论
     * @param articleID 文章id
     * @return 父评论列表
     */
    Result<List<CommentParent>> getCommentList(long articleID);

    /**
     * 获得同类下的文章列表
     * @param articleID 文章id
     * @return 文章列表
     */
    Result<List<Article>> getClassifyNominate(long articleID);

    /**
     * 获取有多少共同标签数的文章列表
     * 以共同标签数倒叙排序
     * @param articleID 文章id
     * @return 文章列表
     */
    Result<List<ArticleTagNominate>> getTagNominate(long articleID);

    /**
     * 保存新的评论
     * @param commentForm 评论信息表单
     * @return 成功
     */
    Result<String> saveComment(CommentForm commentForm);
}
