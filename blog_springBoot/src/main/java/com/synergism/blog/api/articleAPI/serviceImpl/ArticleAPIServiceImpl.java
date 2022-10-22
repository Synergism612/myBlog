package com.synergism.blog.api.articleAPI.serviceImpl;

import com.synergism.blog.api.articleAPI.entity.Pagination;
import com.synergism.blog.core.article.entity.ArticleInformation;
import com.synergism.blog.core.article.enumeration.ArticleSort;
import com.synergism.blog.api.articleAPI.service.ArticleAPIService;
import com.synergism.blog.core.article.service.ArticleService;
import com.synergism.blog.result.Result;
import com.synergism.blog.utils.StringUtil;
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
    public Result<Pagination> getPagination(int currentPage, int pageSize, ArticleSort articleSort,String username) {
        int startIndex = (currentPage - 1) * pageSize;
        int endIndex = currentPage * pageSize;
        List<ArticleInformation> result;
        if (StringUtil.isEmpty(username)){
            result = service.getArticleInformationList();
        }else {
            result = service.getArticleInformationListByUsername(username);
        }
        result = service.ArticleInformationListSort(result,articleSort);
        int total = result.size();
        endIndex = Math.min(endIndex, total);
        return Result.success(new Pagination(result.subList(startIndex, endIndex), total));
    }
}
