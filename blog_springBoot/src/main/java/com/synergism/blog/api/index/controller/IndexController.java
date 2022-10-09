package com.synergism.blog.api.index.controller;


import com.synergism.blog.api.index.entity.ArticleInformation;
import com.synergism.blog.api.index.entity.Pagination;
import com.synergism.blog.api.index.service.IndexService;
import com.synergism.blog.core.article.service.ArticleService;
import com.synergism.blog.result.entity.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/blog/index")
public class IndexController {

    private final IndexService service;
    private final ArticleService articleService;

    @Autowired
    public IndexController(IndexService service, ArticleService articleService) {
        this.service = service;
        this.articleService = articleService;
    }


    @GetMapping("/article")
    public Result<Pagination> article(@RequestParam(defaultValue = "1") int currentPage, @RequestParam(defaultValue = "10") int pageSize) {

        return service.article(currentPage,pageSize);
    }
}
