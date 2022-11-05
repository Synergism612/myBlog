package com.synergism.blog.api.homepageAPI.service;

import com.synergism.blog.api.homepageAPI.entity.FavoriteForm;
import com.synergism.blog.core.favorite.entity.FavoriteInformation;
import com.synergism.blog.core.user.entity.Author;
import com.synergism.blog.result.Result;

import java.util.List;

public interface HomepageAPIService {

    /**
     * 获取账号对应的作者信息
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

    /**
     * 删除收藏夹中的收藏
     * @param favoriteID 收藏夹id
     * @param collectionIDList 收藏id列表
     * @return 成功
     */
    Result<String> deleteCollection(Long favoriteID, List<Long> collectionIDList);

    /**
     * 保存新的收藏夹
     * @param favoriteForm 收藏夹信息表单
     * @return 成功
     */
    Result<String> saveFavorite(FavoriteForm favoriteForm);

    /**
     * 更新收藏夹
     * @param favoriteForm 收藏夹信息表单
     * @return 成功
     */
    Result<String> updateFavorite(FavoriteForm favoriteForm);
}
