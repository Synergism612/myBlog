package com.synergism.blog.api.enshrineAPI.controller;

import com.synergism.blog.api.enshrineAPI.entity.CollectionForm;
import com.synergism.blog.api.enshrineAPI.service.EnshrineAPIService;
import com.synergism.blog.core.favorite.entity.Favorite;
import com.synergism.blog.result.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import java.util.List;

/**
 * 收藏添加组件接口
 */
@RestController
@RequestMapping("/api/blog/enshrine")
public class EnshrineAPIController {

    EnshrineAPIService service;

    @Autowired
    EnshrineAPIController(EnshrineAPIService service){
        this.service = service;
    }


    /**
     * 收藏添加获取收藏夹列表接口
     * @param username 账号
     * @return 收藏夹列表
     */
    //需要登录验证
    @Validated
    @GetMapping("favorite")
    public Result<List<Favorite>> getFavorite(@RequestParam @NotEmpty(message = "账号不能为空") String username){
        return service.getFavorite(username);
    }

    /**
     * 收藏添加保存接口
     * @param collectionForm 收藏信息
     * @return 成功
     */
    //需要登录验证
    @PostMapping("collection")
    public Result<String> saveCollection(@RequestBody @Valid CollectionForm collectionForm){
        return service.saveCollection(collectionForm);
    }

}
