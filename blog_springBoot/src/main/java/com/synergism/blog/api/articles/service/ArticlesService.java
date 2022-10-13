package com.synergism.blog.api.articles.service;

import com.synergism.blog.api.articles.entity.Pagination;

public interface ArticlesService {
    Pagination getIndexArticleInformation(int currentPage, int pageSize);
}
