package com.synergism.blog.core.favorite.entity;

import com.synergism.blog.core.collection.entity.Collection;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class MyFavorite extends Favorite{
    private List<Collection> collectionList;
}
