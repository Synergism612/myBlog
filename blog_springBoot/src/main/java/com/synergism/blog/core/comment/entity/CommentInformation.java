package com.synergism.blog.core.comment.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.synergism.blog.core.user.entity.UserInformation;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
public class CommentInformation {
    private Long id;

    private String body;

    private String likeCount;

    private Long parentID;

    private CommentsFamily parent;

    private List<CommentsFamily> children = new ArrayList<>();

    private UserInformation userInformation;

    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date creationTime;

    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date modifyTime;

    public CommentInformation(Comment comment, UserInformation userInformation) {
        this.id = comment.getId();
        this.body = comment.getBody();
        this.parentID = comment.getParentId();
        this.parent = null;
        this.likeCount = comment.getLikeCount();
        this.userInformation = userInformation;
        this.creationTime = comment.getCreationTime();
        this.modifyTime = comment.getModifyTime();
    }

    public void putSon(CommentsFamily son){
        this.children.add(son);
    }
}