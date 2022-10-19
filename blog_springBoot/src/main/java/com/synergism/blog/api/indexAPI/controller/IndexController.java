package com.synergism.blog.api.indexAPI.controller;


import com.synergism.blog.api.articleAPI.entity.Pagination;
import com.synergism.blog.core.article.enumeration.ArticleSort;
import com.synergism.blog.api.indexAPI.service.IndexService;
import com.synergism.blog.core.user.entity.UserInformation;
import com.synergism.blog.result.CodeMsg;
import com.synergism.blog.result.Result;
import org.apache.commons.lang3.EnumUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/blog/index")
public class IndexController {

    private final IndexService service;

    @Autowired
    public IndexController(IndexService service) {
        this.service = service;
    }

    @GetMapping("/article")
    public Result<Pagination> article(@RequestParam(defaultValue = "1") int currentPage, @RequestParam(defaultValue = "10") int pageSize,@RequestParam String articleSort) {
        if(!EnumUtils.isValidEnum(ArticleSort.class,articleSort)){
            return Result.error(CodeMsg.BIND_ERROR.fillArgs("排序字段错误"));
        }
        return service.getArticles(currentPage,pageSize, ArticleSort.valueOf(articleSort));
    }

    @GetMapping("/userInfo")
    public Result<UserInformation> userInfo() {
        return service.getUserInfo();
    }
}
