package com.synergism.blog.api.contentAPI.controller;


import com.synergism.blog.api.contentAPI.entity.Content;
import com.synergism.blog.api.contentAPI.service.ContentAPIService;
import com.synergism.blog.core.article.entity.Article;
import com.synergism.blog.core.classify.entity.Classify;
import com.synergism.blog.core.comment.entity.CommentParent;
import com.synergism.blog.core.tag.entity.Tag;
import com.synergism.blog.core.user.entity.Author;
import com.synergism.blog.result.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/blog/content")
public class ContentAPIController {

    private final ContentAPIService service;

    @Autowired
    public ContentAPIController(ContentAPIService service) {
        this.service = service;
    }

    @GetMapping("article")
    public Result<Article> article(@RequestParam long id){
        return service.getArticle(id);
    }

    @GetMapping("author")
    public Result<Author> author(@RequestParam long id){
        return service.getAuthor(id);
    }

    @GetMapping("classify")
    public Result<Classify> classify(@RequestParam long id){
        return service.getClassify(id);
    }

    @GetMapping("tagList")
    public Result<List<Tag>> tagList(@RequestParam long id){
        return service.getTagList(id);
    }

    @GetMapping("commentList")
    public Result<List<CommentParent>> commentList(@RequestParam long id){
        return service.getCommentList(id);
    }
}
