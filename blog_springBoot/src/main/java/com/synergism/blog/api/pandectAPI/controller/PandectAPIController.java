package com.synergism.blog.api.pandectAPI.controller;


import com.synergism.blog.api.pandectAPI.service.PandectAPIService;
import com.synergism.blog.core.classify.entity.Classify;
import com.synergism.blog.core.tag.entity.Tag;
import com.synergism.blog.result.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/blog/pandect")
public class PandectAPIController {

    private final PandectAPIService service;

    @Autowired
    public PandectAPIController(PandectAPIService service) {
        this.service = service;
    }

    @GetMapping("classify")
    public Result<List<Classify>> classify(){
        return service.getClassifyList();
    }

    @GetMapping("tag")
    public Result<List<Tag>> tag(){
        return service.getTagList();
    }
}
