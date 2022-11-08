package com.synergism.blog.api.writeAPI.service;


import com.synergism.blog.api.writeAPI.entity.ArticleForm;
import com.synergism.blog.core.classify.entity.ClassifyInformation;
import com.synergism.blog.core.tag.entity.TagInformation;
import com.synergism.blog.result.Result;

import java.util.List;

public interface writeAPIService {

    /**
     * 获取分类
     * @param username 账号
     * @return 分类列表
     */
    Result<List<ClassifyInformation>> getClassifyList(String username);

    /**
     * 获取标签
     * @param username 账号
     * @return 标签列表
     */
    Result<List<TagInformation>> getTagList(String username);

    /**
     * 保存文章
     * @param articleForm 文章信息表单
     * @return 成功
     */
    Result<String> saveArticle(ArticleForm articleForm);
}