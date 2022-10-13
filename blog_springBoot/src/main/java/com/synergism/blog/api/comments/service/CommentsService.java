package com.synergism.blog.api.comments.service;

import com.synergism.blog.api.comments.entity.Comments;

import java.util.List;

public interface CommentsService {
    List<Comments> getIndexComments();
}
