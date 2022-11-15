package com.synergism.blog.api.homepageAPI.controller;

import com.synergism.blog.api.homepageAPI.entity.FavoriteForm;
import com.synergism.blog.api.homepageAPI.entity.UserInformationForm;
import com.synergism.blog.api.homepageAPI.service.HomepageAPIService;
import com.synergism.blog.core.favorite.entity.FavoriteInformation;
import com.synergism.blog.core.user.entity.Author;
import com.synergism.blog.result.CodeMsg;
import com.synergism.blog.result.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
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
    @Validated
    @GetMapping("author")
    public Result<Author> getAuthor(
            @RequestParam @NotEmpty(message = "账号不能为空") String username
    ) {
        return service.getAuthor(username);
    }

    /**
     * 个人主页删除收藏
     *
     * @param username 账号
     * @param favoriteID       收藏夹id
     * @param collectionIDList 收藏id列表
     * @return 成功
     */
    //需要登录验证
    @Validated
    @DeleteMapping("collection")
    public Result<String> removeCollection(
            @RequestParam @NotEmpty(message = "账号不能为空") String username,
            @RequestParam @NotNull(message = "收藏夹不存在") @Min(value = 1,message = "收藏夹不存在") Long favoriteID,
            @RequestParam @Size(min = 1,message = "收藏不存在") List<Long> collectionIDList
    ) {
        return service.removeCollection(username,favoriteID, collectionIDList);
    }

    //需要登录验证
    @GetMapping("favorite")
    public Result<List<FavoriteInformation>> getMyFavoriteList(
            @RequestParam @NotEmpty(message = "账号不能为空") String username
    ) {
        return service.getMyFavoriteList(username);
    }

    //需要登录验证
    @PostMapping("favorite")
    public Result<String> saveFavorite(@RequestBody @Valid FavoriteForm favoriteForm) {
        return service.saveFavorite(favoriteForm);
    }

    //需要登录验证
    @PutMapping("favorite")
    public Result<String> updateFavorite(@RequestBody @Valid FavoriteForm favoriteForm) {
        if (favoriteForm.getId() == null) return Result.error(CodeMsg.BIND_ERROR.fillArgs("收藏夹不存在"));
        return service.updateFavorite(favoriteForm);
    }

    //需要登录验证
    @DeleteMapping("favorite")
    public Result<String> removeFavorite(
            @RequestParam @NotEmpty(message = "账号不能为空") String username,
            @RequestParam @Min(value = 1,message = "收藏夹不存在") Long favoriteID
    ) {
        return service.removeFavorite(username, favoriteID);
    }

    //需要登录验证
    @PutMapping("userInformation")
    public Result<String> updateUserInformation(@RequestBody @Valid UserInformationForm userInformationForm) {
        return service.updateUserInformation(userInformationForm);
    }

}
