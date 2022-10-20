package com.synergism.blog.api.articleAPI.serviceImpl;

import com.synergism.blog.api.articleAPI.entity.Pagination;
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
    public Result<Pagination> getPagination(int currentPage, int pageSize, ArticleSort articleSort) {
        int startIndex = (currentPage - 1) * pageSize;
        int endIndex = currentPage * pageSize;
        List<ArticleInformation> articleInformationList = service.getArticleInformationList(articleSort);
        int total = articleInformationList.size();
        endIndex = Math.min(endIndex, total);
        return Result.success(new Pagination(articleInformationList.subList(startIndex, endIndex), total));
    }
}
