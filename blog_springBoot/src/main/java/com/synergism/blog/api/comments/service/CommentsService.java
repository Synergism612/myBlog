package com.synergism.blog.api.comments.service;

import com.synergism.blog.api.comments.entity.Comments;

import java.util.List;

public interface CommentsService {
    /**
     * 获取所有评论信息
     * @return 评论列表
     */
    List<Comments> getAllComments();

    /**
     * 获取主页评论信息
     * @return 评论列表
     */
    List<Comments> getIndexComments();
}
