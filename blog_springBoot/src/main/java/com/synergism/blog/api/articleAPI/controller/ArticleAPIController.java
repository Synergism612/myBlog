package com.synergism.blog.api.articleAPI.controller;

import com.synergism.blog.core.article.entity.Pagination;
import com.synergism.blog.api.articleAPI.service.ArticleAPIService;
import com.synergism.blog.core.article.enumeration.ArticleSort;
import com.synergism.blog.result.CodeMsg;
import com.synergism.blog.result.Result;
import org.apache.commons.lang3.EnumUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 博客文章组件接口
 */
@RestController
@RequestMapping("/api/blog/article")
public class ArticleAPIController {

    private final ArticleAPIService service;

    @Autowired
    public ArticleAPIController(ArticleAPIService service) {
        this.service = service;
    }

    /**
     * 主页文章获取接口
     * @param currentPage 页数
     * @param pageSize 页容
     * @param articleSort 排序
     * @param username 账号
     * @return 分页后的文章信息
     */
    @GetMapping("index")
    public Result<Pagination> username(@RequestParam(defaultValue = "1") int currentPage, @RequestParam(defaultValue = "10") int pageSize, @RequestParam String articleSort,@RequestParam String username){
        if (!EnumUtils.isValidEnum(ArticleSort.class, articleSort)) {
            return Result.error(CodeMsg.BIND_ERROR.fillArgs("排序字段错误"));
        }
        return service.getPagination(currentPage, pageSize, ArticleSort.valueOf(articleSort),username,"",null,null);
    }

    /**
     * 搜索文章获取接口
     * @param currentPage 页数
     * @param pageSize 页容
     * @param articleSort 排序
     * @param username 账号
     * @param keyword 关键字
     * @param classifyIDList 分类列表
     * @param tagIDList 标签列表
     * @return 分页后的文章信息
     */
    @GetMapping("search")
    public Result<Pagination> search(@RequestParam(defaultValue = "1") int currentPage, @RequestParam(defaultValue = "10") int pageSize, @RequestParam String articleSort, @RequestParam String username, @RequestParam String keyword, @RequestParam List<Long> classifyIDList, @RequestParam List<Long> tagIDList){
        if (!EnumUtils.isValidEnum(ArticleSort.class, articleSort)) {
            return Result.error(CodeMsg.BIND_ERROR.fillArgs("排序字段错误"));
        }
        if (classifyIDList.get(0)==-1) classifyIDList = null;
        if (tagIDList.get(0)==-1) tagIDList = null;

        return service.getPagination(currentPage, pageSize, ArticleSort.valueOf(articleSort),username, keyword,classifyIDList,tagIDList);
    }
}
