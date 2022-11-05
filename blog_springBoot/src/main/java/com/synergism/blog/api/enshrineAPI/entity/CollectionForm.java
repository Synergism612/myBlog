package com.synergism.blog.api.enshrineAPI.entity;

import lombok.Getter;
import lombok.Setter;

/**
 * 添加收藏接口类
 */
@Setter
@Getter
public class CollectionForm {
    private String title;
    private String href;
    private String synopsis;
    private Long favoriteID;
}
