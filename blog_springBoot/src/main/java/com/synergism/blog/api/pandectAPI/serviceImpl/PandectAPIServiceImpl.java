package com.synergism.blog.api.pandectAPI.serviceImpl;

import com.synergism.blog.api.pandectAPI.service.PandectAPIService;
import com.synergism.blog.core.classify.entity.Classify;
import com.synergism.blog.core.classify.service.ClassifyService;
import com.synergism.blog.core.tag.entity.Tag;
import com.synergism.blog.core.tag.service.TagService;
import com.synergism.blog.result.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PandectAPIServiceImpl implements PandectAPIService {

    private final ClassifyService classifyService;
    private final TagService tagService;

    @Autowired
    public PandectAPIServiceImpl(ClassifyService classifyService, TagService tagService) {
        this.classifyService = classifyService;
        this.tagService = tagService;
    }

    @Override
    public Result<List<Classify>> getClassifyList() {
        List<Classify> result = classifyService.list();
        return Result.success(result.size()==0?null:result);
    }

    @Override
    public Result<List<Tag>> getTagList() {
        List<Tag> result = tagService.list();
        return Result.success(result.size()==0?null:result);
    }
}
