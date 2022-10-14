package com.synergism.blog.api.comments.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.synergism.blog.api.user.entity.UserInformation;
import com.synergism.blog.core.comment.entity.Comment;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class Comments {
    private Long id;

    private String body;

    private String likeCount;

    private Long fatherID;

    private Comments father;

    private UserInformation userInformation;

    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date creationTime;

    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date modifyTime;

    public Comments(Comment comment,UserInformation userInformation) {
        this.id = comment.getId();
        this.body = comment.getBody();
        this.fatherID = comment.getFatherId();
        this.father = null;
        this.likeCount = comment.getLikeCount();
        this.userInformation = userInformation;
        this.creationTime = comment.getCreationTime();
        this.modifyTime = comment.getModifyTime();
    }

    public Comments() {
        this.id = -1L;
        this.body = "";
        this.likeCount = "";
        this.fatherID = -1L;
        this.father = null;
        this.userInformation = null;
        this.creationTime = null;
        this.modifyTime = null;
    }
}
