package com.synergism.blog.api.comments.service;

import com.synergism.blog.core.comment.entity.CommentInformation;
import com.synergism.blog.result.entity.Result;

import java.util.List;

public interface CommentsService {


    /**
     * 获取主页评论信息
     * @return 评论列表
     */
    Result<List<CommentInformation>> getIndexComments();
}
