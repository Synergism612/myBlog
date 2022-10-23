package com.synergism.blog.core.comment.service;

import com.synergism.blog.core.comment.entity.Comment;
import com.baomidou.mybatisplus.extension.service.IService;
import com.synergism.blog.core.comment.entity.CommentInformation;

import java.util.List;

/**
 * <p>
 * 评论表 服务类
 * </p>
 *
 * @author Synergism
 * @since 2022-10-10 09:53:06
 */
public interface CommentService extends IService<Comment> {


    /**
     * 通过文章id查询评论信息列表
     * @param articleID 文章id
     * @return 评论信息列表
     */
    List<CommentInformation> getAllCommentInformationListByArticleID(long articleID);

    /**
     * 通过文章id查询根评论
     * 包含该根评论下点赞量前三的子评论
     * @param articleID 文章id
     * @return 评论信息列表
     */
    List<CommentInformation> getTopListByArticleID(long articleID);
}
