package com.synergism.blog.api.contentAPI.entity;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * 新增评论接口类
 */
@Setter
@Getter
public class CommentForm {
    //账号
    @NotEmpty(message = "账号不能为空")
    private String username;
    //评论内容
    @NotEmpty(message = "评论内容不能为空")
    @Size(max = 500,message = "评论内容不能超过500字")
    private String comment;
    //文章id
    @NotNull(message = "文章不存在")
    @Min(value = 1,message = "文章不存在")
    private Long articleID;
    //根评论id
    private Long rootID;
    //父评论内容
    private Long parentID;
}
