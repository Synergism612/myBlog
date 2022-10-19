package com.synergism.blog.core.comment.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.synergism.blog.core.user.entity.UserInformation;
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

    public CommentsFamily(CommentInformation commentInformation) {
        this.id = commentInformation.getId();
        this.body = commentInformation.getBody();
        this.likeCount = commentInformation.getLikeCount();
        this.userInformation = commentInformation.getUserInformation();
        this.creationTime = commentInformation.getCreationTime();
        this.modifyTime = commentInformation.getModifyTime();
    }
}
