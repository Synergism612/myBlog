package com.synergism.blog.api.pandectAPI.service;

import com.synergism.blog.core.classify.entity.Classify;
import com.synergism.blog.core.tag.entity.Tag;
import com.synergism.blog.result.Result;

import java.util.List;

public interface PandectAPIService {

    Result<List<Classify>> getClassifyList();


    Result<List<Tag>> getTagList();
}
