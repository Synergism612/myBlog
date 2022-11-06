package com.synergism.blog.api.pandectAPI.serviceImpl;

import com.synergism.blog.api.pandectAPI.service.PandectAPIService;
import com.synergism.blog.core.article.entity.Pagination;
import com.synergism.blog.core.article.enumeration.ArticleSort;
import com.synergism.blog.core.article.service.ArticleService;
import com.synergism.blog.core.classify.entity.ClassifyInformation;
import com.synergism.blog.core.classify.service.ClassifyService;
import com.synergism.blog.core.tag.entity.TagInformation;
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
    public Result<List<ClassifyInformation>> getClassifyList(String username) {
        List<ClassifyInformation> result;
        if (username.isEmpty()){
            result = classifyService.getAllClassifyInformationList();

        }else{
            result = classifyService.getClassifyInformationListByUsername(username);
        }
        return Result.success(result.size()==0?null:result);
    }

    @Override
    public Result<List<TagInformation>> getTagList(String username) {
        List<TagInformation> result;
        if (username.isEmpty()){
            result = tagService.getAllTagInformationList();
        }else{
            result = tagService.getTagInformationListByUsername(username);
        }
        return Result.success(result.size()==0?null:result);
    }

    @Override
    public Result<Pagination> getArticle(int currentPage, int pageSize, ArticleSort articleSort, String username, String keyword, List<Long> classifyIDList, List<Long> tagIDList) {
        return Result.success(articleService.getPagination(currentPage,pageSize,articleSort,username,keyword,classifyIDList,tagIDList));
    }
}
