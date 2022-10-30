package com.synergism.blog.api.articleAPI.serviceImpl;

import com.synergism.blog.core.article.entity.Pagination;
import com.synergism.blog.core.article.entity.ArticleInformation;
import com.synergism.blog.core.article.enumeration.ArticleSort;
import com.synergism.blog.api.articleAPI.service.ArticleAPIService;
import com.synergism.blog.core.article.service.ArticleService;
import com.synergism.blog.result.Result;
import com.synergism.blog.utils.StringUtil;
import com.synergism.blog.utils.TypeUtil;
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
    public Result<Pagination> getPagination(int currentPage, int pageSize, ArticleSort articleSort) {
        List<ArticleInformation> result = service.getAllArticleInformationList();
        return Result.success(service.Pagination(result, currentPage, pageSize));
    }

    @Override
    public Result<Pagination> getPagination(int currentPage, int pageSize, ArticleSort articleSort, String username, String keyword, List<Long> classifyIDList, List<Long> tagIDList) {
        List<ArticleInformation> result;
        if (StringUtil.isEmpty(username)) {
            result = service.getArticleInformationListByPublic();
        } else {
            result = service.getArticleInformationListByUsername(username);
        }
        if (!StringUtil.isEmpty(keyword) && !TypeUtil.isNull(result)) {
            result = service.searchArticleInformationListByKeyword(result, keyword);
        }
        if (!TypeUtil.isNull(classifyIDList) && !TypeUtil.isNull(result)) {
            result = service.searchArticleInformationListByClassifyList(result, classifyIDList);
        }
        if (!TypeUtil.isNull(tagIDList) && !TypeUtil.isNull(result)) {
            result = service.searchArticleInformationListByTagList(result, tagIDList);
        }
        if (!TypeUtil.isNull(result)) {
            result = service.ArticleInformationListSort(result, articleSort);
        }
        return Result.success(service.Pagination(result, currentPage, pageSize));
    }
}
