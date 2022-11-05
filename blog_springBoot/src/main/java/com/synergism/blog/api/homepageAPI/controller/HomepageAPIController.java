package com.synergism.blog.api.homepageAPI.controller;

import com.synergism.blog.api.homepageAPI.entity.FavoriteForm;
import com.synergism.blog.api.homepageAPI.service.HomepageAPIService;
import com.synergism.blog.core.favorite.entity.FavoriteInformation;
import com.synergism.blog.core.user.entity.Author;
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
     * @param username 账号
     * @return 作者信息
     */
    //需要登录验证
    @GetMapping("author")
    public Result<Author> getAuthor(@RequestParam String username){
        return service.getAuthor(username);
    }

    /**
     * 个人主页删除收藏
     * @param favoriteID 收藏夹id
     * @param collectionIDList 收藏id列表
     * @return 成功
     */
    //需要登录验证
    @DeleteMapping("collection")
    public Result<String> deleteCollection(@RequestParam Long favoriteID,@RequestParam List<Long>collectionIDList ){
        return service.deleteCollection(favoriteID,collectionIDList);
    }

    //需要登录验证
    @GetMapping("favorite")
    public Result<List<FavoriteInformation>> getMyFavoriteList(@RequestParam String username){
        return service.getMyFavoriteList(username);
    }

    //需要登录验证
    @PostMapping("favorite")
    public Result<String> saveFavorite(@RequestBody FavoriteForm favoriteForm){
        return service.saveFavorite(favoriteForm);
    }

}
