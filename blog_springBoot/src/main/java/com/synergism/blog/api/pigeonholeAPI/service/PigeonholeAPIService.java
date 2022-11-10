package com.synergism.blog.api.pigeonholeAPI.service;


import com.synergism.blog.core.article.entity.Archive;
import com.synergism.blog.result.Result;

import java.util.List;

public interface PigeonholeAPIService {

    Result<List<Archive>> getArchive(String username);
}
