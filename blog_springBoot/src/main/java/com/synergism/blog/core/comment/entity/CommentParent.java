package com.synergism.blog.core.comment.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class CommentParent extends CommentInformation{
    private List<CommentChild> commentChildList;

    private int childCount;

    public CommentParent(CommentInformation commentInformation, List<CommentChild> commentChildList,int childCount) {
        super(commentInformation);
        this.commentChildList = commentChildList;
        this.childCount = childCount;
    }
}
