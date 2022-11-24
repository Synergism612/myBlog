package com.synergism.blog.api.pandectAPI.controller;


import com.synergism.blog.api.pandectAPI.service.PandectAPIService;
import com.synergism.blog.core.article.entity.Pagination;
import com.synergism.blog.core.article.enumeration.ArticleSort;
import com.synergism.blog.core.classify.entity.Classify;
import com.synergism.blog.core.tag.entity.Tag;
import com.synergism.blog.result.CodeMsg;
import com.synergism.blog.result.Result;
import org.apache.commons.lang3.EnumUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotEmpty;
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
     *
     * @param currentPage    页数
     * @param pageSize       页容
     * @param articleSort    排序
     * @param username       账号
     * @param keyword        关键字
     * @param classifyIDList 分类列表
     * @param tagIDList      标签列表
     * @return 分页后的文章信息
     */
    @GetMapping("article")
    public Result<Pagination> getArticle(
            @RequestParam(defaultValue = "1") int currentPage,
            @RequestParam(defaultValue = "10") int pageSize,
            @RequestParam @NotEmpty(message = "排序字段错误") String articleSort,
            @RequestParam String username,
            @RequestParam String keyword,
            @RequestParam List<Long> classifyIDList,
            @RequestParam List<Long> tagIDList) {
        if (!EnumUtils.isValidEnum(ArticleSort.class, articleSort)) {
            return Result.error(CodeMsg.BIND_ERROR.fillArgs("排序字段错误"));
        }
        if (!keyword.isEmpty()) {
            if (keyword.length() >= 30) {
                return Result.error(CodeMsg.MESSAGE.fillArgs("查询字段不能超过30字"));
            }
        }
        if (classifyIDList.get(0) == -1) classifyIDList = null;
        if (tagIDList.get(0) == -1) tagIDList = null;

        return service.getArticle(currentPage, pageSize, ArticleSort.valueOf(articleSort), username, keyword, classifyIDList, tagIDList);
    }

    /**
     * 文章专栏获取分类接口
     *
     * @param username 账号 可以为空
     * @return 分类列表
     */
    @Validated
    @GetMapping("classify")
    public Result<List<Classify>> getClassifyList(@RequestParam @NotEmpty(message = "用户不存在") String username) {
        return service.getClassifyList(username);
    }

    /**
     * 文章专栏获取标签接口
     *
     * @param username 账号 可以为空
     * @return 标签列表
     */
    @Validated
    @GetMapping("tag")
    public Result<List<Tag>> getTag(@RequestParam @NotEmpty(message = "用户不存在") String username) {
        return service.getTagList(username);
    }
}
