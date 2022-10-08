package com.synergism.blog.core.article.controller;

import com.synergism.blog.core.article.entity.Article;
import com.synergism.blog.core.article.entity.Pagination;
import com.synergism.blog.core.article.service.ArticleService;
import com.synergism.blog.result.entity.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 文章表 前端控制器
 * </p>
 *
 * @author Synergism
 * @since 2022-10-07 10:07:37
 */
@RestController
@RequestMapping("/api/blog/article")
public class ArticleController {

    private final ArticleService service;

    @Autowired
    ArticleController(ArticleService service) {
        this.service = service;
    }

    @GetMapping("/pagination")
    public Result<Pagination> Pagination(@RequestParam int currentPage, @RequestParam int pageSize) {

        return service.pagination(currentPage,pageSize);
    }

}
