package com.synergism.blog.api.enshrineAPI.service;

import com.synergism.blog.api.enshrineAPI.entity.AddCollection;
import com.synergism.blog.api.enshrineAPI.entity.AddFavoriteGroup;
import com.synergism.blog.core.collection.entity.Collection;
import com.synergism.blog.core.favorite.entity.Favorite;
import com.synergism.blog.result.Result;

import java.util.List;

public interface EnshrineAPIService {

    /**
     * 根据账户获取收藏夹列表
     * @param username 账户
     * @return 收藏夹列表
     */
    Result<List<Favorite>> getFavorite(String username);

    Result<String> setFavorite(AddFavoriteGroup addFavoriteGroup);

    Result<String> setCollection(AddCollection addCollection);

    Result<List<Collection>> getCollection(Long favoriteID);
}
