package com.synergism.blog.api.comments.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.synergism.blog.api.user.entity.UserInformation;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class CommentsFamily {
    private Long id;

    private String body;

    private String likeCount;


    private UserInformation userInformation;

    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date creationTime;

    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date modifyTime;

    public CommentsFamily(Comments comments) {
        this.id = comments.getId();
        this.body = comments.getBody();
        this.likeCount = comments.getLikeCount();
        this.userInformation = comments.getUserInformation();
        this.creationTime = comments.getCreationTime();
        this.modifyTime = comments.getModifyTime();
    }
    public CommentsFamily() {
        this.id = -1L;
        this.body = "";
        this.likeCount = "";
        this.userInformation = null;
        this.creationTime = null;
        this.modifyTime = null;
    }
}
