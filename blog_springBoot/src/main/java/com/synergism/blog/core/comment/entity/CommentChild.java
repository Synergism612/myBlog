package com.synergism.blog.core.comment.entity;

import lombok.Getter;
import lombok.Setter;

/**
 * 子评论信息
 */
@Getter
@Setter
public class CommentChild extends CommentInformation {
    //父评论所属账号
    private String parentUserName;
    //父评论所属昵称
    private String parentNickname;

    public CommentChild(CommentInformation commentInformation, String parentNickname) {
        super(commentInformation);
        this.parentNickname = parentNickname;
    }
}
