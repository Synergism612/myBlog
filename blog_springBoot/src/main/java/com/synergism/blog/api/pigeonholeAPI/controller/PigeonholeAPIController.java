package com.synergism.blog.api.pigeonholeAPI.controller;

import com.synergism.blog.api.pigeonholeAPI.service.PigeonholeAPIService;
import com.synergism.blog.core.article.entity.Archive;
import com.synergism.blog.result.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 归档页面接口
 */
@RestController
@RequestMapping("/api/blog/pigeonhole")
public class PigeonholeAPIController {

    private final PigeonholeAPIService service;

    @Autowired
    public PigeonholeAPIController(PigeonholeAPIService service) {
        this.service = service;
    }

    /**
     * 归档获取档案接口
     * @param username 账号 可以为空
     * @return 档案记录
     */
    @GetMapping("archive")
    public Result<List<Archive>> getArchive (@RequestParam String username){
        return service.getArchive(username);
    }
}
