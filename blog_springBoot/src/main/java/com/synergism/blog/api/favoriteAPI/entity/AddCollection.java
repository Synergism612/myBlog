package com.synergism.blog.api.favoriteAPI.entity;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AddCollection {
    private String title;
    private String url;
    private String synopsis;
    private Long groupID;
}
