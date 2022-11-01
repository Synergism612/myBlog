package com.synergism.blog.api.homepageAPI.controller;

import com.synergism.blog.api.homepageAPI.service.HomepageAPIService;
import com.synergism.blog.core.user.entity.Author;
import com.synergism.blog.result.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/blog/homepage")
public class HomepageAPIController {

    private final HomepageAPIService service;

    @Autowired
    public HomepageAPIController(HomepageAPIService service) {
        this.service = service;
    }

    //需要登录验证
    @GetMapping("author")
    public Result<Author> getAuthor(@RequestParam String username){
        return service.getAuthor(username);
    }

}
