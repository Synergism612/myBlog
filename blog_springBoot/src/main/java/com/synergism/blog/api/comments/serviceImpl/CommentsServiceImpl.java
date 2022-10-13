package com.synergism.blog.api.comments.serviceImpl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.synergism.blog.api.comments.entity.Comments;
import com.synergism.blog.api.comments.service.CommentsService;
import com.synergism.blog.core.comment.entity.Comment;
import com.synergism.blog.core.comment.service.CommentService;
import com.synergism.blog.utils.TypeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentsServiceImpl implements CommentsService {

    private final CommentService commentService;

    @Autowired
    public CommentsServiceImpl(CommentService commentService) {
        this.commentService = commentService;
    }

    @Override
    public List<Comments> getIndexComments() {
        //获得所有评论
        List<Comment> commentList = commentService.list();

        //获得点赞数最多的五条评论
        List<Comment> commentTopList = commentService.list(new QueryWrapper<Comment>().orderByDesc("like_count").last("limit 5"));
        //整理父级评论
        return commentTopList.stream().map(top -> {
            if (!TypeUtil.ifNull(top.getFatherId())){
                for (Comment father : commentList) {
                    if (father.getId().equals(top.getFatherId()))
                        return new Comments(top,father);
                }
            }
            return  new Comments(top);
        }).collect(Collectors.toList());
    }
}
