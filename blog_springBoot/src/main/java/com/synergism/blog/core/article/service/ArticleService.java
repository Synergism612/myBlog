package com.synergism.blog.core.article.service;

import com.synergism.blog.core.article.entity.Article;
import com.baomidou.mybatisplus.extension.service.IService;
import com.synergism.blog.core.article.entity.ArticleInformation;
import com.synergism.blog.core.article.enumeration.ArticleSort;
import com.synergism.blog.core.article.mapper.ArticleMapper;

import java.util.List;


/**
 * <p>
 * 文章表 服务类
 * </p>
 *
 * @author Synergism
 * @since 2022-10-07 10:07:37
 */
public interface ArticleService extends IService<Article> {

    /**
     * 获取排序后的文章信息列表
     * @param articleInformationList 需要排序的文章信息列表
     * @param articleSort 排序字段
     * @return 文章信息列表
     */
    List<ArticleInformation> ArticleInformationListSort(List<ArticleInformation> articleInformationList,ArticleSort articleSort);

    /**
     * 获取文章信息列表
     * @return 文章信息列表
     */
    List<ArticleInformation> getArticleInformationList();

    /**
     * 获取该用户下文章信息列表
     * @param username 用户名
     * @return 文章信息列表
     */
    List<ArticleInformation> getArticleInformationListByUsername(String username);

}
