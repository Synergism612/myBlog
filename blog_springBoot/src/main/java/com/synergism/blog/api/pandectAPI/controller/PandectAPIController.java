package com.synergism.blog.api.pandectAPI.controller;


import com.synergism.blog.api.pandectAPI.service.PandectAPIService;
import com.synergism.blog.core.article.entity.Pagination;
import com.synergism.blog.core.article.enumeration.ArticleSort;
import com.synergism.blog.core.classify.entity.ClassifyInformation;
import com.synergism.blog.core.tag.entity.TagInformation;
import com.synergism.blog.result.CodeMsg;
import com.synergism.blog.result.Result;
import org.apache.commons.lang3.EnumUtils;
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

    /**
     * 文章专栏搜索文章获取接口
     * @param currentPage 页数
     * @param pageSize 页容
     * @param articleSort 排序
     * @param username 账号
     * @param keyword 关键字
     * @param classifyIDList 分类列表
     * @param tagIDList 标签列表
     * @return 分页后的文章信息
     */
    @GetMapping("article")
    public Result<Pagination> getArticle(@RequestParam(defaultValue = "1") int currentPage, @RequestParam(defaultValue = "10") int pageSize, @RequestParam String articleSort, @RequestParam String username, @RequestParam String keyword, @RequestParam List<Long> classifyIDList, @RequestParam List<Long> tagIDList){
        if (!EnumUtils.isValidEnum(ArticleSort.class, articleSort)) {
            return Result.error(CodeMsg.BIND_ERROR.fillArgs("排序字段错误"));
        }
        if (classifyIDList.get(0)==-1) classifyIDList = null;
        if (tagIDList.get(0)==-1) tagIDList = null;

        return service.getArticle(currentPage, pageSize, ArticleSort.valueOf(articleSort),username, keyword,classifyIDList,tagIDList);
    }

    /**
     * 文章专栏获取分类接口
     * @param username 账号
     * @return 分类列表
     */
    @GetMapping("classify")
    public Result<List<ClassifyInformation>> getClassifyList(@RequestParam String username){
        return service.getClassifyList(username);
    }

    /**
     文章专栏获取标签接口
     * @param username 账号
     * @return 标签列表
     */
    @GetMapping("tag")
    public Result<List<TagInformation>> getTag(@RequestParam String username){
        return service.getTagList(username);
    }
}
