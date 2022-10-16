package com.synergism.blog.core.article_classify.service;

import com.synergism.blog.core.article_classify.entity.ArticleClassify;
import com.baomidou.mybatisplus.extension.service.IService;
import com.synergism.blog.core.classify.entity.Classify;

import java.util.List;

/**
 * <p>
 * 分类-文章对照表：文章的分类 服务类
 * </p>
 *
 * @author Synergism
 * @since 2022-10-11 05:57:44
 */
public interface ArticleClassifyService extends IService<ArticleClassify> {

    /**
     * 通过文章ID列表获得分类列表
     * @param articleIDList 文章ID列表
     * @return 对应的分类列表
     */
    List<List<Classify>> getClassifyListByArticleIDList(List<Long> articleIDList);
}
