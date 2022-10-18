package com.synergism.blog.api.indexAPI.controller;


import com.synergism.blog.api.articleAPI.entity.Pagination;
import com.synergism.blog.api.articleAPI.enumeration.OrderBy;
import com.synergism.blog.core.comment.entity.CommentInformation;
import com.synergism.blog.api.indexAPI.service.IndexService;
import com.synergism.blog.api.userAPI.entity.UserInformation;
import com.synergism.blog.result.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/blog/index")
public class IndexController {

    private final IndexService service;

    @Autowired
    public IndexController(IndexService service) {
        this.service = service;
    }


    @GetMapping("/article")
    public Result<Pagination> article(@RequestParam(defaultValue = "1") int currentPage, @RequestParam(defaultValue = "10") int pageSize,String orderBy) {

        return service.getArticles(currentPage,pageSize, OrderBy.valueOf(orderBy));
    }

    @GetMapping("/userInfo")
    public Result<UserInformation> userInfo() {
        return service.getUserInfo();
    }
}
