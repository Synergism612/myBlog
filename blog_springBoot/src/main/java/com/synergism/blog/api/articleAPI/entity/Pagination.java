package com.synergism.blog.api.articleAPI.entity;

import com.synergism.blog.core.article.entity.ArticleInformation;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Pagination {
    //文章信息列表
    private final List<ArticleInformation> articleInformationList;
    private final int total; //总数

    public Pagination(List<ArticleInformation> articleInformationList, int total) {
        this.articleInformationList = articleInformationList;
        this.total = total;
    }
}
