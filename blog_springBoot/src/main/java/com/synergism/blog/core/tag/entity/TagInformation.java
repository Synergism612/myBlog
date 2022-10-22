package com.synergism.blog.core.tag.entity;

import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel(value = "Tag对象", description = "标签表")
public class TagInformation extends Tag {

    //标签下文章数
    private int articleCount;
    //标签所属账号
    private String username;
    //标签所属账号昵称
    private String nickname;
}
