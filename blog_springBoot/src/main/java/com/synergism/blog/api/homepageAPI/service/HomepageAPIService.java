package com.synergism.blog.api.homepageAPI.service;

import com.synergism.blog.core.user.entity.Author;
import com.synergism.blog.result.Result;

public interface HomepageAPIService {

    Result<Author> getAuthor(String username);
}
