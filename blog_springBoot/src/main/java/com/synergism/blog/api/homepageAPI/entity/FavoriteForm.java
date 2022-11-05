package com.synergism.blog.api.homepageAPI.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FavoriteForm {

    //账号
    private String username;
    //分组名称
    private String name;
    //注释
    private String annotation;
    //私有  0-公开 1-私有
    private Integer ifPrivate;
}
