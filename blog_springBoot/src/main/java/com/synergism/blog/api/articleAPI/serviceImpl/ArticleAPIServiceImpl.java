package com.synergism.blog.api.articleAPI.serviceImpl;
import com.synergism.blog.api.articleAPI.entity.Pagination;
import com.synergism.blog.api.articleAPI.enumeration.OrderBy;
import com.synergism.blog.api.articleAPI.service.ArticleAPIService;
import com.synergism.blog.result.Result;
import org.springframework.stereotype.Service;

@Service
public class ArticleAPIServiceImpl implements ArticleAPIService {
    @Override
    public Result<Pagination> getPagination(int currentPage, int pageSize, OrderBy orderBy) {
        return Result.success(null);
    }
}
