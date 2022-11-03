package com.synergism.blog.core.article.entity;

import lombok.Getter;
import lombok.Setter;

/**
 * 文章同标签数
 */
@Setter
@Getter
public class ArticleTagNominate extends Article{
    //查询与某文章的同标签数量
    private int tagCount;
}
