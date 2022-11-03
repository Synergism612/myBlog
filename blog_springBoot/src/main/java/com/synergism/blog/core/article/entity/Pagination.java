package com.synergism.blog.core.article.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * 分页信息
 */
@Getter
@Setter
public class Pagination {
    //文章信息列表
    private final List<ArticleInformation> articleInformationList;
    //总数
    private final int total;

    public Pagination(List<ArticleInformation> articleInformationList, int total) {
        this.articleInformationList = articleInformationList;
        this.total = total;
    }
}
