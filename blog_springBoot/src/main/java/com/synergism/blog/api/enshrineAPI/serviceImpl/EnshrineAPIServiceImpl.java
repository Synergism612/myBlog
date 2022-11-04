package com.synergism.blog.api.enshrineAPI.serviceImpl;

import com.synergism.blog.api.enshrineAPI.entity.AddCollection;
import com.synergism.blog.api.enshrineAPI.service.EnshrineAPIService;
import com.synergism.blog.core.favorite.entity.Favorite;
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

    public EnshrineAPIServiceImpl(UserService userService, FavoriteService favoriteService) {
        this.userService = userService;
        this.favoriteService = favoriteService;
    }

    @Override
    public Result<List<Favorite>> getFavorite(String username) {
        if (!userService.isExist(username)) return Result.error(CodeMsg.BIND_ERROR.fillArgs("用户错误"));
        return Result.success(favoriteService.getListByUsername(username));
    }

    @Override
    public Result<String> saveCollection(AddCollection addCollection) {
        if (!favoriteService.isExist(addCollection.getFavoriteID()))
            return Result.error(CodeMsg.BIND_ERROR.fillArgs("不存在该收藏夹"));
        if (favoriteService.isExist(addCollection.getFavoriteID(), addCollection.getHref()))
            return Result.error(CodeMsg.MESSAGE.fillArgs("您已收藏"));
        return favoriteService.save(addCollection.getTitle(), addCollection.getHref(), addCollection.getSynopsis(), addCollection.getFavoriteID())
                ? Result.success()
                : Result.error(CodeMsg.BIND_ERROR.fillArgs("收藏夹错误"));
    }
}
