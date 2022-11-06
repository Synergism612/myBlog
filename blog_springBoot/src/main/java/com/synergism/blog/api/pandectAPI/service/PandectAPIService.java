package com.synergism.blog.api.pandectAPI.service;

import com.synergism.blog.core.article.entity.Pagination;
import com.synergism.blog.core.article.enumeration.ArticleSort;
import com.synergism.blog.core.classify.entity.Classify;
import com.synergism.blog.core.classify.entity.ClassifyInformation;
import com.synergism.blog.core.tag.entity.Tag;
import com.synergism.blog.core.tag.entity.TagInformation;
import com.synergism.blog.result.Result;

import java.util.List;

public interface PandectAPIService {

    Result<List<ClassifyInformation>> getClassifyList(String username);


    Result<List<TagInformation>> getTagList(String username);

    /**
     * 文章专栏搜索文章获取接口
     * @param currentPage 页数
     * @param pageSize 页容
     * @param articleSort 排序
     * @param username 账号
     * @param keyword 关键字
     * @param classifyIDList 分类列表
     * @param tagIDList 标签列表
     * @return 分页后的文章信息
     */
    Result<Pagination> getArticle(int currentPage, int pageSize, ArticleSort articleSort, String username, String keyword, List<Long> classifyIDList, List<Long> tagIDList);
}
