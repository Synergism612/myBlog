package com.synergism.blog.api.homepageAPI.service;

import com.synergism.blog.core.favorite.entity.FavoriteInformation;
import com.synergism.blog.core.user.entity.Author;
import com.synergism.blog.result.Result;

import java.util.List;

public interface HomepageAPIService {

    /**
     * 根据账号获取作者信息
     * @param username 账号
     * @return 作者信息
     */
    Result<Author> getAuthor(String username);

    /**
     * 获取我的收藏夹
     * 以及收藏夹下的收藏列表
     * @param username 账号
     * @return 我的收藏夹列表
     */
    Result<List<FavoriteInformation>> getMyFavoriteList(String username);

    Result<String> deleteCollection(Long favoriteID, List<Long> collection);
}
