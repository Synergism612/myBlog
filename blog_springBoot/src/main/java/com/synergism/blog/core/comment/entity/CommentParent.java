package com.synergism.blog.core.comment.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * 父评论信息
 */
@Setter
@Getter
public class CommentParent extends CommentInformation{
    //子评论列表
    private List<CommentChild> commentChildList;
    //子评论数
    private int childCount;

    public CommentParent(CommentInformation commentInformation, List<CommentChild> commentChildList,int childCount) {
        super(commentInformation);
        this.commentChildList = commentChildList;
        this.childCount = childCount;
    }
}
