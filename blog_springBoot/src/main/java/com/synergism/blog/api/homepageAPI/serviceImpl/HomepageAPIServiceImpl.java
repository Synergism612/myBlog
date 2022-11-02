package com.synergism.blog.api.homepageAPI.serviceImpl;

import com.synergism.blog.api.enshrineAPI.entity.AddFavoriteGroup;
import com.synergism.blog.api.homepageAPI.service.HomepageAPIService;
import com.synergism.blog.core.favorite.entity.MyFavorite;
import com.synergism.blog.core.favorite.service.FavoriteService;
import com.synergism.blog.core.user.entity.Author;
import com.synergism.blog.core.user.service.UserService;
import com.synergism.blog.result.CodeMsg;
import com.synergism.blog.result.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HomepageAPIServiceImpl implements HomepageAPIService {

    private final UserService userService;

    private final FavoriteService favoriteService;

    @Autowired
    public HomepageAPIServiceImpl(UserService userService, FavoriteService favoriteService) {
        this.userService = userService;
        this.favoriteService = favoriteService;
    }

    @Override
    public Result<Author> getAuthor(String username) {
       return Result.success(userService.getAuthorByUsername(username));
    }

    @Override
    public Result<List<MyFavorite>> getFavorite(String username) {
        long userID = userService.getID(username);
        if (userID == -1) return Result.error(CodeMsg.BIND_ERROR.fillArgs("用户错误"));
        return Result.success(favoriteService.getFavorite(userID));
    }


    @Override
    public Result<String> saveFavorite(AddFavoriteGroup addFavoriteGroup) {
        long userID = userService.getID(addFavoriteGroup.getUsername());
        if (userID == -1) return Result.error(CodeMsg.BIND_ERROR.fillArgs("用户错误"));
        return favoriteService.save(addFavoriteGroup.getName(), addFavoriteGroup.getAnnotation(), addFavoriteGroup.getIf_private(), userID)
                ? Result.success()
                : Result.error(CodeMsg.BIND_ERROR.fillArgs("创建失败"));
    }

}
