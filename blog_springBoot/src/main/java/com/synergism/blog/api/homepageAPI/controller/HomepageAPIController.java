package com.synergism.blog.api.homepageAPI.controller;

import com.synergism.blog.api.homepageAPI.entity.FavoriteForm;
import com.synergism.blog.api.homepageAPI.entity.UserInformationForm;
import com.synergism.blog.api.homepageAPI.service.HomepageAPIService;
import com.synergism.blog.core.favorite.entity.FavoriteInformation;
import com.synergism.blog.core.user.entity.Author;
import com.synergism.blog.result.CodeMsg;
import com.synergism.blog.result.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 个人主页接口
 */
@RestController
@RequestMapping("/api/blog/homepage")
public class HomepageAPIController {

    private final HomepageAPIService service;

    @Autowired
    public HomepageAPIController(HomepageAPIService service) {
        this.service = service;
    }

    /**
     * 个人主页获取我的作者信息
     *
     * @param username 账号
     * @return 作者信息
     */
    //需要登录验证
    @GetMapping("author")
    public Result<Author> getAuthor(@RequestParam String username) {
        return service.getAuthor(username);
    }

    /**
     * 个人主页删除收藏
     *
     * @param favoriteID       收藏夹id
     * @param collectionIDList 收藏id列表
     * @return 成功
     */
    //需要登录验证
    @DeleteMapping("collection")
    public Result<String> removeCollection(@RequestParam Long favoriteID, @RequestParam List<Long> collectionIDList) {
        if (collectionIDList.size() == 0) return Result.error(CodeMsg.BIND_ERROR.fillArgs("收藏不存在"));
        if (favoriteID == null) return Result.error(CodeMsg.BIND_ERROR.fillArgs("收藏夹不存在"));
        return service.removeCollection(favoriteID, collectionIDList);
    }

    //需要登录验证
    @GetMapping("favorite")
    public Result<List<FavoriteInformation>> getMyFavoriteList(@RequestParam String username) {
        return service.getMyFavoriteList(username);
    }

    //需要登录验证
    @PostMapping("favorite")
    public Result<String> saveFavorite(@RequestBody FavoriteForm favoriteForm) {
        return service.saveFavorite(favoriteForm);
    }

    //需要登录验证
    @PutMapping("favorite")
    public Result<String> updateFavorite(@RequestBody FavoriteForm favoriteForm) {
        if (favoriteForm.getId() == null) return Result.error(CodeMsg.BIND_ERROR.fillArgs("收藏夹不存在"));
        return service.updateFavorite(favoriteForm);
    }


    //需要登录验证
    @DeleteMapping("favorite")
    public Result<String> removeFavorite(@RequestParam String username, @RequestParam Long favoriteID) {
        if (username.isEmpty()) return Result.error(CodeMsg.BIND_ERROR.fillArgs("用户不存在"));
        if (favoriteID == null) return Result.error(CodeMsg.BIND_ERROR.fillArgs("收藏夹不存在"));
        return service.removeFavorite(username, favoriteID);
    }

    //需要登录验证
    @PutMapping("userInformation")
    public Result<String> updateUserInformation(@RequestBody UserInformationForm userInformationForm) {
        if (userInformationForm.getUsername().isEmpty()) return Result.error(CodeMsg.BIND_ERROR.fillArgs("用户不存在"));
        if (userInformationForm.getNickname().isEmpty()) return Result.error(CodeMsg.BIND_ERROR.fillArgs("昵称不能为空"));
        if (userInformationForm.getSex() == null) return Result.error(CodeMsg.BIND_ERROR.fillArgs("性别代码不能为空"));
        if (userInformationForm.getSex() < 0 || userInformationForm.getSex() > 1)
            return Result.error(CodeMsg.BIND_ERROR.fillArgs("性别代码错误"));
        return service.updateUserInformation(userInformationForm);
    }

}
