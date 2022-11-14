package com.synergism.blog.api.writeAPI.controller;

import com.synergism.blog.api.writeAPI.entity.ArticleForm;
import com.synergism.blog.api.writeAPI.entity.ClassifyForm;
import com.synergism.blog.api.writeAPI.entity.TagForm;
import com.synergism.blog.api.writeAPI.service.writeAPIService;
import com.synergism.blog.core.article.entity.ArticleInformation;
import com.synergism.blog.core.classify.entity.Classify;
import com.synergism.blog.core.tag.entity.Tag;
import com.synergism.blog.result.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
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
     * 创作页面获取分类接口
     * @return 分类列表
     */
    @GetMapping("classify")
    public Result<List<Classify>> getClassifyList(){
        return service.getClassifyList();
    }

    /**
     * 创作页面获取标签接口
     * @return 标签列表
     */
    @GetMapping("tag")
    public Result<List<Tag>> getTag(){
        return service.getTagList();
    }

    /**
     * 创作页面获取文章接口
     * @param articleID 文章id
     * @return 标签列表
     */
    @Validated
    @GetMapping("article")
    public Result<ArticleInformation> getArticle(
            @RequestParam @NotNull(message = "文章id不能为空") @Min(value = 1,message = "文章不存在") Long articleID
    ){
        return service.getArticle(articleID);
    }

    /**
     * 创作页面保存文章接口
     * @param articleForm 文章信息表单
     * @return 成功
     */
    @PostMapping("article")
    public Result<String> saveArticle(@RequestBody @Valid ArticleForm articleForm){
        if (articleForm.getSynopsis().isEmpty()){
            articleForm.setSynopsis(articleForm.getBody().substring(0,20));
        }
        return service.saveArticle(articleForm);
    }

    /**
     * 创作页面更新文章接口
     * @param articleForm 文章信息表单
     * @return 成功
     */
    @PutMapping("article")
    public Result<String> updateArticle(@RequestBody @Valid ArticleForm articleForm){
        if (articleForm.getSynopsis().isEmpty()){
            articleForm.setSynopsis(articleForm.getBody().substring(0,20));
        }
        return service.updateArticle(articleForm);
    }

    /**
     * 创作页面保存分类接口
     * @param classifyForm 分类信息表单
     * @return 成功
     */
    @PostMapping("classify")
    public Result<String> saveClassify(@RequestBody @Valid ClassifyForm classifyForm){
        return service.saveClassify(classifyForm);
    }

    /**
     * 创作页面保存标签接口
     * @param tagForm 标签信息表单
     * @return 成功
     */
    @PostMapping("tag")
    public Result<String> saveTag(@RequestBody @Valid TagForm tagForm){
        return service.saveTag(tagForm);
    }
}
