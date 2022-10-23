package com.synergism.blog.api.contentAPI.controller;


import com.synergism.blog.api.contentAPI.entity.Content;
import com.synergism.blog.api.contentAPI.service.ContentAPIService;
import com.synergism.blog.result.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/blog/content")
public class ContentAPIController {

    private final ContentAPIService service;

    @Autowired
    public ContentAPIController(ContentAPIService service) {
        this.service = service;
    }

    @GetMapping
    public Result<Content> getContent(@RequestParam long id, @RequestParam String title){

        return service.getContent(id,title);
    }

}
