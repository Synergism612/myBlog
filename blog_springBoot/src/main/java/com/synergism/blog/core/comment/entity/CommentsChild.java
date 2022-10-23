package com.synergism.blog.core.comment.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CommentsChild extends CommentInformation {
    private String parentNickname;

    public CommentsChild(CommentInformation commentInformation, String parentNickname) {
        super(commentInformation);
        this.parentNickname = parentNickname;
    }
}
