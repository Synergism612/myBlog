package com.synergism.blog.core.comment.service;

import com.synergism.blog.api.contentAPI.entity.AddComment;
import com.synergism.blog.core.comment.entity.Comment;
import com.baomidou.mybatisplus.extension.service.IService;
import com.synergism.blog.core.comment.entity.CommentInformation;
import com.synergism.blog.core.comment.entity.CommentParent;
import com.synergism.blog.result.Result;

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
     * 包含该根评论下所有的子评论
     * @param articleID 文章id
     * @return 评论信息列表
     */
    List<CommentParent> getAllListByArticleID(long articleID);

    /**
     * 保存新的评论
     * @param body 评论内容
     * @param rootId 根评论id
     * @param parentId 父评论id
     * @param articleID 文章id
     * @param userID 用户id
     * @return 成功为真，反之为假
     */
    boolean save(String body,Long rootId,Long parentId,long articleID,long userID);

    /**
     * 根据评论id判断是否存在
     * @param commentID 评论id
     * @return 存在为真，反之为假
     */
    boolean isExist(Long commentID);
}
