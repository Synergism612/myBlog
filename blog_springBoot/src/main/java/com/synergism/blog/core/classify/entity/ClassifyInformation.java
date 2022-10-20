package com.synergism.blog.core.classify.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClassifyInformation extends Classify{
    //分类下文章数
    private int article_count;
    //分类所属账号
    private String username;
    //分类所属账号昵称
    private String nickname;
}
