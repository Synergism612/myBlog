package com.synergism.blog.api.homepageAPI.serviceImpl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
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
    public Result<List<FavoriteInformation>> getMyFavoriteList(String username) {
        if (!userService.isExist(username)){
            return Result.error(CodeMsg.BIND_ERROR.fillArgs("账号错误"));
        }
        return Result.success(favoriteService.getFavoriteInformationListByUsername(username));
    }

    @Override
    public Result<String> deleteCollection(Long favoriteID, List<Long> collectionIDList) {
        return favoriteService.remove(favoriteID, collectionIDList) ? Result.success() : Result.error(CodeMsg.MESSAGE.fillArgs("删除失败"));
    }

    @Override
    public Result<String> saveFavorite(FavoriteForm favoriteForm) {
        long userID = userService.getID(favoriteForm.getUsername());
        if (userID==-1){
            return Result.error(CodeMsg.BIND_ERROR.fillArgs("账号错误"));
        }
        return favoriteService.save(favoriteForm.getName(),favoriteForm.getAnnotation(),favoriteForm.getIfPrivate(),userID)
                ?Result.success()
                :Result.error(CodeMsg.MESSAGE.fillArgs("添加失败"));
    }

    @Override
    public Result<String> updateFavorite(FavoriteForm favoriteForm) {
        if (favoriteService.isExist(favoriteForm.getId())){
            return favoriteService.updateById(new Favorite(favoriteForm.getId(),favoriteForm.getName(),favoriteForm.getAnnotation(),favoriteForm.getIfPrivate()))
                    ?Result.success()
                    :Result.error(CodeMsg.MESSAGE.fillArgs("更新失败"));
        }
        return Result.error(CodeMsg.BIND_ERROR.fillArgs("收藏夹不存在"));
    }

    @Override
    public Result<String> deleteFavorite(String username, Long favoriteID) {
        long userID = userService.getID(username);
        if (userID!=-1){
            return favoriteService.remove(userID,favoriteID)
                    ?Result.success()
                    :Result.error(CodeMsg.MESSAGE.fillArgs("删除失败"));
        }
        return Result.error(CodeMsg.BIND_ERROR.fillArgs("用户不存在"));
    }

    @Override
    public Result<String> updateUserInformation(UserInformationForm userInformationForm) {
        User user = userService.getOne(new LambdaQueryWrapper<User>().eq(User::getUsername,userInformationForm.getUsername()));
        if (user!=null){
            user.setNickname(userInformationForm.getNickname());
            user.setBirthday(TimeUtil.toDate(userInformationForm.getBirthday()));
            user.setSex(userInformationForm.getSex());
            user.setIntro(userInformationForm.getIntro());
            return userService.updateById(user)
                    ?Result.success()
                    :Result.error(CodeMsg.MESSAGE.fillArgs("删除失败"));
        }
        return Result.error(CodeMsg.BIND_ERROR.fillArgs("用户不存在"));
    }

}
