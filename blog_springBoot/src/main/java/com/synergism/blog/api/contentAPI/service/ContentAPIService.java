package com.synergism.blog.api.contentAPI.service;

import com.synergism.blog.api.contentAPI.entity.Content;
import com.synergism.blog.core.article.entity.Article;
import com.synergism.blog.result.Result;

public interface ContentAPIService {
    Result<Content> getContent(long id, String title);
}
