package com.synergism.blog.api.contentAPI.entity;

import lombok.Getter;
import lombok.Setter;

/**
 * 新增评论接口类
 */
@Setter
@Getter
public class CommentForm {
    private String username;
    private String comment;
    private Long articleID;
    private Long rootID;
    private Long parentID;
}
