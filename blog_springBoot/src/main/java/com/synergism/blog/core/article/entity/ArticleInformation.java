package com.synergism.blog.core.article.entity;


import com.synergism.blog.core.classify.entity.Classify;
import com.synergism.blog.core.tag.entity.Tag;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * 文章详细信息
 */
@Getter
@Setter
public class ArticleInformation extends Article {
     //所属者昵称
    private String nickname;
    //所属者账号
    private String username;
    //评论数
    private int commentCount;
    //分类
    private Classify classify;
    //标签
    private List<Tag> tagList;
}
