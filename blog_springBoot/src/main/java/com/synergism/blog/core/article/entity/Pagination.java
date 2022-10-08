package com.synergism.blog.core.article.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Pagination {
    //文章信息列表
    private List<Article> articleList;
    //分页信息
    int currentPage; //页数
    int pageSize; //页内条数
    long total; //总数

    public Pagination(List<Article> articleList, int currentPage, int pageSize, long total) {
        this.articleList = articleList;
        this.currentPage = currentPage;
        this.pageSize = pageSize;
        this.total = total;
    }
}
