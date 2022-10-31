package com.synergism.blog.api.enshrineAPI.entity;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AddCollection {
    private String title;
    private String href;
    private String synopsis;
    private Long favoriteID;
}
