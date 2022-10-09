package com.synergism.blog.api.index.service;

import com.synergism.blog.api.index.entity.Pagination;
import com.synergism.blog.result.entity.Result;

public interface IndexService {
    Result<Pagination> article(int currentPage, int pageSize);
}
