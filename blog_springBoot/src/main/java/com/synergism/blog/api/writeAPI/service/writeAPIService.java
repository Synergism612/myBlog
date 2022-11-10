package com.synergism.blog.api.writeAPI.service;


import com.synergism.blog.api.writeAPI.entity.ArticleForm;
import com.synergism.blog.core.article.entity.ArticleInformation;
import com.synergism.blog.core.classify.entity.Classify;
import com.synergism.blog.core.classify.entity.ClassifyInformation;
import com.synergism.blog.core.tag.entity.Tag;
import com.synergism.blog.core.tag.entity.TagInformation;
import com.synergism.blog.result.Result;

import java.util.List;

public interface writeAPIService {

    /**
     * 获取分类
     * @return 分类列表
     */
    Result<List<Classify>> getClassifyList();

    /**
     * 获取标签
     * @return 标签列表
     */
    Result<List<Tag>> getTagList();

    /**
     * 获取文章
     * @param articleID 文章id
     * @return 文章信息
     */
    Result<ArticleInformation> getArticle(Long articleID);

    /**
     * 保存文章
     * @param articleForm 文章信息表单
     * @return 成功
     */
    Result<String> saveArticle(ArticleForm articleForm);

    /**
     * 更新文章
     * @param articleForm 文章信息表单
     * @return 成功
     */
    Result<String> updateArticle(ArticleForm articleForm);
}
