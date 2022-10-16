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
     * 获取所有评论信息
     * @return 评论信息列表
     */
    List<CommentInformation> getAllComment();
}
