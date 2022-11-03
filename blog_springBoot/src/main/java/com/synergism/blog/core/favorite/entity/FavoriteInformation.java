package com.synergism.blog.core.favorite.entity;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class FavoriteInformation extends Favorite {

    //所属用户昵称
    private String nickname;
    //所属用户账户
    private String username;
}
