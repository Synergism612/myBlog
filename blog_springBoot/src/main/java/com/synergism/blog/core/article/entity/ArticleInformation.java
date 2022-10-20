package com.synergism.blog.core.article.entity;


import com.synergism.blog.core.classify.entity.Classify;
import com.synergism.blog.core.tag.entity.Tag;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ArticleInformation extends Article {
     //所属者昵称
    private String nickname;
    //所属者账号
    private String username;
    //是否私有
    private int ifPrivate;
    //分类
    private List<Classify> classifyList;
    //标签
    private List<Tag> tagList;
}