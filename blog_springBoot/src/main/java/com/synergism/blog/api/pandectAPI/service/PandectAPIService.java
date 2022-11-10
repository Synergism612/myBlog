package com.synergism.blog.api.pandectAPI.service;

import com.synergism.blog.core.article.entity.Pagination;
import com.synergism.blog.core.article.enumeration.ArticleSort;
import com.synergism.blog.core.classify.entity.Classify;
import com.synergism.blog.core.tag.entity.Tag;
import com.synergism.blog.result.Result;

import java.util.List;

public interface PandectAPIService {

    /**
     * 获取分类
     * @param username 账号
     * @return 分类列表
     */
    Result<List<Classify>> getClassifyList(String username);

    /**
     * 获取标签
     * @param username 账号
     * @return 标签列表
     */
    Result<List<Tag>> getTagList(String username);

    /**
     * 获取分页
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
