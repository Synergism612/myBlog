package com.synergism.blog.api.writeAPI.controller;

import com.synergism.blog.api.writeAPI.entity.ArticleForm;
import com.synergism.blog.api.writeAPI.service.writeAPIService;
import com.synergism.blog.core.classify.entity.ClassifyInformation;
import com.synergism.blog.core.tag.entity.TagInformation;
import com.synergism.blog.result.CodeMsg;
import com.synergism.blog.result.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 博客创作页面接口
 */
@RestController
@RequestMapping("/api/blog/write")
public class WriteAPIController {

    private final writeAPIService service;

    @Autowired
    public WriteAPIController(writeAPIService service) {
        this.service = service;
    }

    /**
     * 创作页面获取分类
     * @param username 账号
     * @return 分类列表
     */
    @GetMapping("classify")
    public Result<List<ClassifyInformation>> getClassifyList(@RequestParam String username){
        if (username.isEmpty()){
            return Result.error(CodeMsg.BIND_ERROR.fillArgs("用户名不能为空"));
        }
        return service.getClassifyList(username);
    }

    /**
     * 创作页面获取标签
     * @param username 账号
     * @return 标签列表
     */
    @GetMapping("tag")
    public Result<List<TagInformation>> getTag(@RequestParam String username){
        if (username.isEmpty()){
            return Result.error(CodeMsg.BIND_ERROR.fillArgs("用户名不能为空"));
        }
        return service.getTagList(username);
    }

    /**
     * 创作页面保存文章接口
     * @param articleForm 文章信息表单
     * @return 成功
     */
    @PostMapping("article")
    public Result<String> saveArticle(@RequestBody ArticleForm articleForm){
        return service.saveArticle(articleForm);
    }
}
