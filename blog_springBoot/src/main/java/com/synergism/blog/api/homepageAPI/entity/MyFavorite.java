package com.synergism.blog.api.homepageAPI.entity;

import com.synergism.blog.core.favorite.entity.Collection;
import com.synergism.blog.core.favorite.entity.Favorite;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class MyFavorite extends Favorite {
    private List<Collection> collectionList;
}
