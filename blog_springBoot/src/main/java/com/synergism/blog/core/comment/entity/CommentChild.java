package com.synergism.blog.core.comment.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentChild extends CommentInformation {
    private String parentUserName;
    private String parentNickname;

    public CommentChild(CommentInformation commentInformation, String parentNickname) {
        super(commentInformation);
        this.parentNickname = parentNickname;
    }
}
