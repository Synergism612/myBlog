package com.synergism.blog.core.article.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class Archive {
    private String year;
    private String month;
    private String date;
    private List<Article> articleList;
}
