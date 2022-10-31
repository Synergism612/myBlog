package com.synergism.blog.api.favoriteAPI.serviceImpl;

import com.synergism.blog.api.favoriteAPI.service.FavoriteAPIService;
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

    public FavoriteAPIServiceImpl(UserService userService, FavoriteService favoriteService) {
        this.userService = userService;
        this.favoriteService = favoriteService;
    }

    @Override
    public Result<List<Favorite>> getGroup(String username) {
        long userID = userService.getID(username);
        if (userID==-1) return Result.error(CodeMsg.BIND_ERROR.fillArgs("用户错误"));
        return Result.success(favoriteService.getFavoriteListByUserID(userID));
    }
}
