package com.synergism.blog.core.comment.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CommentInformation extends Comment {

    private List<CommentsChild> children;

    private String username;
    private String icon;
    private String nickname;

    public CommentInformation(CommentInformation commentInformation) {
        super(commentInformation);
        this.children = commentInformation.children;
        this.username = commentInformation.username;
        this.icon = commentInformation.icon;
        this.nickname = commentInformation.nickname;
    }
}
