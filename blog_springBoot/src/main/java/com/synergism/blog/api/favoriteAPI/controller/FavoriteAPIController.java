package com.synergism.blog.api.favoriteAPI.controller;

import com.synergism.blog.api.favoriteAPI.entity.AddCollection;
import com.synergism.blog.api.favoriteAPI.entity.AddFavoriteGroup;
import com.synergism.blog.api.favoriteAPI.service.FavoriteAPIService;
import com.synergism.blog.core.favorite.entity.Favorite;
import com.synergism.blog.result.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/favorite")
public class FavoriteAPIController {

    FavoriteAPIService service;

    @Autowired
    FavoriteAPIController(FavoriteAPIService service){
        this.service = service;
    }


    //需要登录验证
    @GetMapping("group")
    public Result<List<Favorite>> group(@RequestParam String username){
        return service.getGroup(username);
    }

    //需要登录验证
    @PostMapping("group")
    public Result<String> group(@RequestBody AddFavoriteGroup addFavoriteGroup){
        return service.setGroup(addFavoriteGroup);
    }

    @PostMapping("collection")
    public Result<String> collection(@RequestBody AddCollection addCollection){
        return service.setCollection(addCollection);
    }

}
