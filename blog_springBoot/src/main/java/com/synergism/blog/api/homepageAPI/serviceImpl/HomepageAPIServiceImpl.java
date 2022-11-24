package com.synergism.blog.api.homepageAPI.serviceImpl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.synergism.blog.api.dbankAPI.service.DBankAPIService;
import com.synergism.blog.api.homepageAPI.entity.FavoriteForm;
import com.synergism.blog.api.homepageAPI.entity.UserInformationForm;
import com.synergism.blog.api.homepageAPI.service.HomepageAPIService;
import com.synergism.blog.core.favorite.entity.Favorite;
import com.synergism.blog.core.favorite.entity.FavoriteInformation;
import com.synergism.blog.core.favorite.service.FavoriteService;
import com.synergism.blog.core.user.entity.Author;
import com.synergism.blog.core.user.entity.User;
import com.synergism.blog.core.user.service.UserService;
import com.synergism.blog.result.CodeMsg;
import com.synergism.blog.result.Result;
import com.synergism.blog.utils.TimeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;

@Service
public class HomepageAPIServiceImpl implements HomepageAPIService {

    //分隔符
    private final String separator = File.separator;

    private final UserService userService;
    private final FavoriteService favoriteService;
    private final DBankAPIService dBankAPIService;

    @Autowired
    public HomepageAPIServiceImpl(UserService userService, FavoriteService favoriteService, DBankAPIService dBankAPIService) {
        this.userService = userService;
        this.favoriteService = favoriteService;
        this.dBankAPIService = dBankAPIService;
    }

    @Override
    public Result<Author> getAuthor(String username) {
        return Result.success(userService.getAuthorByUsername(username));
    }

    @Override
    public Result<List<FavoriteInformation>> getMyFavoriteList(String username) {
        if (!userService.isExist(username)) {
            return Result.error(CodeMsg.BIND_ERROR.fillArgs("账号错误"));
        }
        return Result.success(favoriteService.getFavoriteInformationListByUsername(username));
    }

    @Override
    public Result<String> removeCollection(String username, long favoriteID, List<Long> collectionIDList) {
        if (!favoriteService.isExist(username, favoriteID)) {
            return Result.error(CodeMsg.BIND_ERROR.fillArgs("收藏夹不存在"));
        }
        favoriteService.remove(favoriteID, collectionIDList);
        return Result.success();
    }

    @Override
    public Result<String> saveFavorite(FavoriteForm favoriteForm) {
        long userID = userService.getID(favoriteForm.getUsername());
        if (userID != -1) {
            favoriteService.save(favoriteForm.getName(), favoriteForm.getAnnotation(), favoriteForm.getIfPrivate(), userID);
            return Result.success();
        }
        return Result.error(CodeMsg.BIND_ERROR.fillArgs("账号错误"));
    }

    @Override
    public Result<String> updateFavorite(FavoriteForm favoriteForm) {
        if (!favoriteService.isExist(favoriteForm.getUsername(), favoriteForm.getId())) {
            return Result.error(CodeMsg.BIND_ERROR.fillArgs("收藏夹不存在不存在"));
        }
        Favorite favorite = favoriteService.getById(favoriteForm.getId());
        if (favorite == null) {
            return Result.error(CodeMsg.BIND_ERROR.fillArgs("收藏夹不存在"));
        }
        favorite.update(favoriteForm.getName(),
                favoriteForm.getAnnotation(),
                favoriteForm.getIfPrivate());
        return favoriteService.updateById(favorite)
                ? Result.success()
                : Result.error(CodeMsg.MESSAGE.fillArgs("更新失败"));
    }

    @Override
    public Result<String> removeFavorite(String username, long favoriteID) {
        long userID = userService.getID(username);
        if (userID == -1) {
            return Result.error(CodeMsg.BIND_ERROR.fillArgs("用户不存在"));
        }
        if (!favoriteService.isExist(username, favoriteID)) {
            return Result.error(CodeMsg.BIND_ERROR.fillArgs("收藏夹不存在不存在"));
        }
        favoriteService.remove(userID, favoriteID);
        return Result.success();
    }

    @Override
    public Result<String> updateUser(UserInformationForm userInformationForm) {
        User user = userService.getOne(new LambdaQueryWrapper<User>().eq(User::getUsername, userInformationForm.getUsername()));
        if (user != null) {
            user.update(user.getIcon(),
                    user.getNickname(),
                    TimeUtil.toDate(userInformationForm.getBirthday()),
                    user.getSex(),
                    user.getIntro());
            return userService.updateById(user)
                    ? Result.success()
                    : Result.error(CodeMsg.MESSAGE.fillArgs("更新失败"));
        }
        return Result.error(CodeMsg.BIND_ERROR.fillArgs("用户不存在"));
    }

    @Override
    public Result<String> saveUserIcon(String username, MultipartFile file) {
        String path = username + separator + "user" + separator + "icon";
        Result<String> result = dBankAPIService.autoSaveFile(username, file, path);
        userService.updateIcon(username,result.getData());
        return result;
    }

}
