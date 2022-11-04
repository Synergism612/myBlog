package com.synergism.blog.core.favorite.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class FavoriteInformation extends Favorite {

    //收藏夹下的收藏
    private List<Collection> collectionList;
    //所属用户昵称
    private String nickname;
    //所属用户账户
    private String username;
}
