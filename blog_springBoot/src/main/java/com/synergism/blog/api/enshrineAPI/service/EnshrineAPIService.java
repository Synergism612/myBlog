package com.synergism.blog.api.favoriteAPI.service;

import com.synergism.blog.api.favoriteAPI.entity.AddCollection;
import com.synergism.blog.api.favoriteAPI.entity.AddFavoriteGroup;
import com.synergism.blog.core.favorite.entity.Favorite;
import com.synergism.blog.result.Result;

import java.util.List;

public interface FavoriteAPIService {

    /**
     * 根据账户获取收藏夹列表
     * @param username 账户
     * @return 收藏夹列表
     */
    Result<List<Favorite>> getGroup(String username);

    Result<String> setGroup(AddFavoriteGroup addFavoriteGroup);

    Result<String> setCollection(AddCollection addCollection);
}
