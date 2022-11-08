package com.synergism.blog.api.indexAPI.controller;

import com.synergism.blog.api.indexAPI.service.IndexAPIService;
import com.synergism.blog.core.article.entity.Pagination;
import com.synergism.blog.core.article.enumeration.ArticleSort;
import com.synergism.blog.core.classify.entity.ClassifyInformation;
import com.synergism.blog.core.tag.entity.TagInformation;
import com.synergism.blog.core.user.entity.UserInformation;
import com.synergism.blog.result.CodeMsg;
import com.synergism.blog.result.Result;
import org.apache.commons.lang3.EnumUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
     * 首页文章获取接口
     * @param currentPage 页数
     * @param pageSize 页容
     * @param articleSort 排序
     * @param username 账号
     * @return 分页后的文章信息
     */
    @GetMapping("article")
    public Result<Pagination> article(@RequestParam(defaultValue = "1") int currentPage, @RequestParam(defaultValue = "10") int pageSize, @RequestParam String articleSort, @RequestParam String username){
        if (!EnumUtils.isValidEnum(ArticleSort.class, articleSort)) {
            return Result.error(CodeMsg.BIND_ERROR.fillArgs("排序字段错误"));
        }
        return service.getArticle(currentPage, pageSize, ArticleSort.valueOf(articleSort),username);
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


    @DeleteMapping("/article")
    public Result<String> removeArticle(@RequestParam String username,@RequestParam List<Long> articleIDList){
        if (articleIDList.size()==0) return Result.error(CodeMsg.BIND_ERROR.fillArgs("文章不存在"));
        if (username.isEmpty()) return Result.error(CodeMsg.BIND_ERROR.fillArgs("用户不存在"));
        return service.removeArticle(username,articleIDList);
    }
}
