package com.synergism.blog.api.contentAPI.entity;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AddComment {
    private String username;
    private String comment;
    private Long articleID;
    private Long rootID;
    private Long parentID;
}
