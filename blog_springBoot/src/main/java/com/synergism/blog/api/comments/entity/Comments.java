package com.synergism.blog.api.comments.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
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

    private Comment father;

    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date creationTime;

    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date modifyTime;

    public Comments(Comment comment,Comment father) {
        this.id = comment.getId();
        this.body = comment.getBody();
        this.father = father;
        this.likeCount = comment.getLikeCount();
        this.creationTime = comment.getCreationTime();
        this.modifyTime = comment.getModifyTime();
    }

    public Comments(Comment comment) {
        this.id = comment.getId();
        this.body = comment.getBody();
        this.father = null;
        this.likeCount = comment.getLikeCount();
        this.creationTime = comment.getCreationTime();
        this.modifyTime = comment.getModifyTime();
    }
}
