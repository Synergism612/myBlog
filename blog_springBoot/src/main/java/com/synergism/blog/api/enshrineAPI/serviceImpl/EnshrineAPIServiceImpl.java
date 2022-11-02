package com.synergism.blog.api.enshrineAPI.serviceImpl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.synergism.blog.api.enshrineAPI.entity.AddCollection;
import com.synergism.blog.api.enshrineAPI.entity.AddFavoriteGroup;
import com.synergism.blog.api.enshrineAPI.service.EnshrineAPIService;
import com.synergism.blog.core.collection.entity.Collection;
import com.synergism.blog.core.collection.service.CollectionService;
import com.synergism.blog.core.favorite.entity.Favorite;
import com.synergism.blog.core.favorite.entity.MyFavorite;
import com.synergism.blog.core.favorite.service.FavoriteService;
import com.synergism.blog.core.user.service.UserService;
import com.synergism.blog.result.CodeMsg;
import com.synergism.blog.result.Result;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EnshrineAPIServiceImpl implements EnshrineAPIService {

    private final UserService userService;
    private final FavoriteService favoriteService;
    private final CollectionService collectionService;

    public EnshrineAPIServiceImpl(UserService userService, FavoriteService favoriteService, CollectionService collectionService) {
        this.userService = userService;
        this.favoriteService = favoriteService;
        this.collectionService = collectionService;
    }

    @Override
    public Result<List<Favorite>> getFavorite(String username) {
        long userID = userService.getID(username);
        if (userID == -1) return Result.error(CodeMsg.BIND_ERROR.fillArgs("用户错误"));
        return Result.success(favoriteService.getFavoriteListByUserID(userID));
    }

    @Override
    public Result<String> saveCollection(AddCollection addCollection) {
        if (favoriteService.isExist(addCollection.getFavoriteID())) {
            if (collectionService.isExist(addCollection.getFavoriteID(), addCollection.getHref())) {
                return Result.error(CodeMsg.MESSAGE.fillArgs("您已收藏"));
            }
        } else {
            return Result.error(CodeMsg.BIND_ERROR.fillArgs("不存在该收藏夹"));
        }

        return collectionService.save(addCollection.getTitle(), addCollection.getHref(), addCollection.getSynopsis(), addCollection.getFavoriteID())
                ? Result.success()
                : Result.error(CodeMsg.BIND_ERROR.fillArgs("收藏夹错误"));
    }
}
