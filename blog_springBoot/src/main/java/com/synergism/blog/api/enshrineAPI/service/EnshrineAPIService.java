package com.synergism.blog.api.enshrineAPI.service;

import com.synergism.blog.api.enshrineAPI.entity.CollectionForm;
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
    /**
     * 添加新的收藏
     * @param collectionForm 收藏信息
     * @return 成功
     */
    Result<String> saveCollection(CollectionForm collectionForm);
}
