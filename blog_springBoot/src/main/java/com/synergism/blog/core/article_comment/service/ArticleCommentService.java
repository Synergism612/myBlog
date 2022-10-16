package com.synergism.blog.core.article_comment.service;

import com.synergism.blog.core.article_comment.entity.ArticleComment;
import com.baomidou.mybatisplus.extension.service.IService;
import com.synergism.blog.core.classify.entity.Classify;
import com.synergism.blog.core.comment.entity.CommentInformation;

import java.util.List;

/**
 * <p>
 * 文章-评论对照表：文章的评论 服务类
 * </p>
 *
 * @author Synergism
 * @since 2022-10-11 05:47:47
 */
public interface ArticleCommentService extends IService<ArticleComment> {
    /**
     * 通过文章ID列表获得评论列表
     * @param articleIDList 文章ID列表
     * @return 评论信息列表
     */
    List<List<CommentInformation>> getCommentInformationListByArticleIDList(List<Long> articleIDList);
}
