package com.synergism.blog.api.favoriteAPI.serviceImpl;

import com.synergism.blog.api.favoriteAPI.entity.AddCollection;
import com.synergism.blog.api.favoriteAPI.entity.AddFavoriteGroup;
import com.synergism.blog.api.favoriteAPI.service.FavoriteAPIService;
import com.synergism.blog.core.collection.service.CollectionService;
import com.synergism.blog.core.favorite.entity.Favorite;
import com.synergism.blog.core.favorite.service.FavoriteService;
import com.synergism.blog.core.user.service.UserService;
import com.synergism.blog.result.CodeMsg;
import com.synergism.blog.result.Result;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FavoriteAPIServiceImpl implements FavoriteAPIService {

    private final UserService userService;
    private final FavoriteService favoriteService;
    private final CollectionService collectionService;

    public FavoriteAPIServiceImpl(UserService userService, FavoriteService favoriteService, CollectionService collectionService) {
        this.userService = userService;
        this.favoriteService = favoriteService;
        this.collectionService = collectionService;
    }

    @Override
    public Result<List<Favorite>> getGroup(String username) {
        long userID = userService.getID(username);
        if (userID == -1) return Result.error(CodeMsg.BIND_ERROR.fillArgs("用户错误"));
        return Result.success(favoriteService.getFavoriteListByUserID(userID));
    }

    @Override
    public Result<String> setGroup(AddFavoriteGroup addFavoriteGroup) {
        long userID = userService.getID(addFavoriteGroup.getUsername());
        if (userID == -1) return Result.error(CodeMsg.BIND_ERROR.fillArgs("用户错误"));
        return favoriteService.save(addFavoriteGroup.getName(), addFavoriteGroup.getAnnotation(), addFavoriteGroup.getIf_private(), userID)
                ? Result.success()
                : Result.error(CodeMsg.BIND_ERROR.fillArgs("创建失败"));
    }

    @Override
    public Result<String> setCollection(AddCollection addCollection) {
        if (favoriteService.isExist(addCollection.getGroupID())){
            return collectionService.save(addCollection.getTitle(), addCollection.getUrl(), addCollection.getSynopsis(), addCollection.getGroupID())
                    ?Result.success()
                    :Result.error(CodeMsg.BIND_ERROR.fillArgs("收藏夹错误"));
        }
        return Result.error(CodeMsg.BIND_ERROR.fillArgs("收藏夹错误"));
    }
}
