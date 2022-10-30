package com.synergism.blog.api.articleAPI.controller;

import com.synergism.blog.api.articleAPI.entity.Pagination;
import com.synergism.blog.api.articleAPI.service.ArticleAPIService;
import com.synergism.blog.core.article.enumeration.ArticleSort;
import com.synergism.blog.result.CodeMsg;
import com.synergism.blog.result.Result;
import org.apache.commons.lang3.EnumUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/blog/article")
public class ArticleAPIController {

    private final ArticleAPIService service;

    @Autowired
    public ArticleAPIController(ArticleAPIService service) {
        this.service = service;
    }

    @GetMapping("all")
    public Result<Pagination> all(@RequestParam(defaultValue = "1") int currentPage, @RequestParam(defaultValue = "10") int pageSize, @RequestParam String articleSort){
        if (!EnumUtils.isValidEnum(ArticleSort.class, articleSort)) {
            return Result.error(CodeMsg.BIND_ERROR.fillArgs("排序字段错误"));
        }
        return service.getPagination(currentPage, pageSize, ArticleSort.valueOf(articleSort),"", "");
    }

    @GetMapping
    public Result<Pagination> username(@RequestParam(defaultValue = "1") int currentPage, @RequestParam(defaultValue = "10") int pageSize, @RequestParam String articleSort,@RequestParam String username){
        if (!EnumUtils.isValidEnum(ArticleSort.class, articleSort)) {
            return Result.error(CodeMsg.BIND_ERROR.fillArgs("排序字段错误"));
        }
        return service.getPagination(currentPage, pageSize, ArticleSort.valueOf(articleSort),username, "");
    }

    @GetMapping("search")
    public Result<Pagination> search(@RequestParam(defaultValue = "1") int currentPage, @RequestParam(defaultValue = "10") int pageSize, @RequestParam String articleSort, @RequestParam String username,@RequestParam String keyword){
        if (!EnumUtils.isValidEnum(ArticleSort.class, articleSort)) {
            return Result.error(CodeMsg.BIND_ERROR.fillArgs("排序字段错误"));
        }
        return service.getPagination(currentPage, pageSize, ArticleSort.valueOf(articleSort),username, keyword);
    }
}
