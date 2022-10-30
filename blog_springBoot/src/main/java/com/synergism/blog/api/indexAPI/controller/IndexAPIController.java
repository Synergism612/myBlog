package com.synergism.blog.api.indexAPI.controller;

import com.synergism.blog.api.indexAPI.service.IndexAPIService;
import com.synergism.blog.core.classify.entity.ClassifyInformation;
import com.synergism.blog.core.tag.entity.TagInformation;
import com.synergism.blog.core.user.entity.UserInformation;
import com.synergism.blog.result.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/blog/index")
public class IndexAPIController {

    private final IndexAPIService service;

    @Autowired
    public IndexAPIController(IndexAPIService service) {
        this.service = service;
    }

    /**
     * 首页默认用户信息
     *
     * @return 结果[用户信息]
     */
    @GetMapping("/userInfo")
    public Result<UserInformation> userInfo() {
        return service.getIndexUserInfo();
    }

    /**
     * 首页标签云
     *
     * @return 结果[标签列表]
     */
    @GetMapping("/tag")
    public Result<List<TagInformation>> tag(@RequestParam String username) {
        return service.getIndexTag(username);
    }

    /**
     * 首页分类云
     *
     * @return 结果[分类列表]
     */
    @GetMapping("/classify")
    public Result<List<ClassifyInformation>> classify(@RequestParam String username) {
        return service.getIndexClassify(username);
    }
}
