package com.synergism.blog.api.articleAPI.serviceImpl;

import com.synergism.blog.core.article.entity.Pagination;
import com.synergism.blog.core.article.entity.ArticleInformation;
import com.synergism.blog.core.article.enumeration.ArticleSort;
import com.synergism.blog.api.articleAPI.service.ArticleAPIService;
import com.synergism.blog.core.article.service.ArticleService;
import com.synergism.blog.result.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticleAPIServiceImpl implements ArticleAPIService {

    private final ArticleService service;

    @Autowired
    public ArticleAPIServiceImpl(ArticleService service) {
        this.service = service;
    }
    @Override
    public Result<Pagination> getPagination(int currentPage, int pageSize, ArticleSort articleSort, String username, String keyword, List<Long> classifyIDList, List<Long> tagIDList) {
        List<ArticleInformation> result;
        //传入账号是否为空
        if (username.isEmpty()) {
            //为空获取公开文章列表
            result = service.getPublicArticleInformationList();
        } else {
            //不为空获取用户下的文章列表
            result = service.getArticleInformationListByUsername(username);
        }
        //传入关键字不为空且暂存结果不为空
        if (!keyword.isEmpty() && result!=null) {
            //根据关键字获取文章列表
            result = service.getArticleInformationListByKeyword(result, keyword);
        }
        //传入分类id列表不为空且暂存结果不为空
        if (classifyIDList!=null && result!=null) {
            //根据分类id列表获取文章列表
            result = service.getArticleInformationListByClassifyList(result, classifyIDList);
        }
        //传入标签id列表不为空且暂存结果不为空
        if (tagIDList!=null && result!=null) {
            //根据标签id列表获取文章列表
            result = service.getArticleInformationListByTagList(result, tagIDList);
        }
        //暂存结果不为空
        if (result!=null) {
            //排序
            result = service.sortArticleInformationList(result, articleSort);
        }
        //封装分页
        return Result.success(service.Pagination(result, currentPage, pageSize));
    }
}
