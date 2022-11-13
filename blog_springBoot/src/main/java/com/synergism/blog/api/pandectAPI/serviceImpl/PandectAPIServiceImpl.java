package com.synergism.blog.api.pandectAPI.serviceImpl;

import com.synergism.blog.api.pandectAPI.service.PandectAPIService;
import com.synergism.blog.core.article.entity.Pagination;
import com.synergism.blog.core.article.enumeration.ArticleSort;
import com.synergism.blog.core.article.service.ArticleService;
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

    private final ArticleService articleService;
    private final ClassifyService classifyService;
    private final TagService tagService;

    @Autowired
    public PandectAPIServiceImpl(ArticleService articleService, ClassifyService classifyService, TagService tagService) {
        this.articleService = articleService;
        this.classifyService = classifyService;
        this.tagService = tagService;
    }

    @Override
    public Result<List<Classify>> getClassifyList(String username) {
        List<Classify> result;
        if (username.isEmpty()){
            result = classifyService.list();
        }else{
            result = classifyService.getUsedListByUsername(username);
        }
        return Result.success(result.size()==0?null:result);
    }

    @Override
    public Result<List<Tag>> getTagList(String username) {
        List<Tag> result;
        if (username.isEmpty()){
            result = tagService.list();
        }else{
            result = tagService.getUsedListByUsername(username);
        }
        return Result.success(result.size()==0?null:result);
    }

    @Override
    public Result<Pagination> getArticle(int currentPage, int pageSize, ArticleSort articleSort, String username, String keyword, List<Long> classifyIDList, List<Long> tagIDList) {
        return Result.success(articleService.getPagination(currentPage,pageSize,articleSort,username,keyword,classifyIDList,tagIDList));
    }
}
