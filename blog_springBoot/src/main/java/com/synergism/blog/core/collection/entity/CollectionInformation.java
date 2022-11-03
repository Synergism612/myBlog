package com.synergism.blog.core.collection.entity;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CollectionInformation extends Collection{
    //所属收藏夹id
    private long favoriteID;
}
