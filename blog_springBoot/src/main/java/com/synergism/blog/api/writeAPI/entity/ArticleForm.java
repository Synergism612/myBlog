package com.synergism.blog.api.writeAPI.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class ArticleForm {
    private String username;

    private Long id;

    private String icon;

    private String title;

    private String body;

    private String synopsis;

    private int ifPrivate;

    private Long classifyID;

    private List<Long> tagIDList;
}
