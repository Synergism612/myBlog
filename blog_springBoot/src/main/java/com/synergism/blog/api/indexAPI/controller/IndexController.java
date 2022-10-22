package com.synergism.blog.api.indexAPI.controller;

import com.synergism.blog.api.articleAPI.entity.Pagination;
import com.synergism.blog.core.article.enumeration.ArticleSort;
import com.synergism.blog.api.indexAPI.service.IndexService;
import com.synergism.blog.core.classify.entity.ClassifyInformation;
import com.synergism.blog.core.tag.entity.TagInformation;
import com.synergism.blog.core.user.entity.UserInformation;
import com.synergism.blog.result.CodeMsg;
import com.synergism.blog.result.Result;
import com.synergism.blog.utils.StringUtil;
import org.apache.commons.lang3.EnumUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/blog/index")
public class IndexController {

    private final IndexService service;

    @Autowired
    public IndexController(IndexService service) {
        this.service = service;
    }

    /**
     * 首页文章接口
     *
     * @param currentPage 页数
     * @param pageSize    页容量
     * @param articleSort 排序
     * @return 结果[分页内容]
     */
    //该函数需要一个验证角色切面
    @GetMapping("/article")
    public Result<Pagination> article(@RequestParam(defaultValue = "1") int currentPage, @RequestParam(defaultValue = "10") int pageSize, @RequestParam String articleSort, @RequestParam String username) {
        if (!EnumUtils.isValidEnum(ArticleSort.class, articleSort)) {
            return Result.error(CodeMsg.BIND_ERROR.fillArgs("排序字段错误"));
        }
        return service.getIndexArticles(currentPage, pageSize, ArticleSort.valueOf(articleSort), username);
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
