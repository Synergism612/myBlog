package com.synergism.blog.core.article.service;

import com.synergism.blog.core.article.entity.Article;
import com.baomidou.mybatisplus.extension.service.IService;
import com.synergism.blog.core.article.entity.ArticleInformation;
import com.synergism.blog.core.article.entity.ArticleTagNominate;
import com.synergism.blog.core.article.entity.Pagination;
import com.synergism.blog.core.article.enumeration.ArticleSort;

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
     * 获取该用户下文章信息列表
     * @param username 用户名
     * @return 文章信息列表||null
     */
    List<ArticleInformation> getArticleInformationListByUsername(String username);

    /**
     * 获取公共的文章信息列表
     * @return 文章信息列表||null
     */
    List<ArticleInformation> getPublicArticleInformationList();

    /**
     * 获取排序后的文章信息列表
     * 该方法要求传入的文章列表不可为null
     * 文章列表size为0时返回null
     * @param articleInformationList 需要排序的文章信息列表
     * @param articleSort 排序字段
     * @return 文章信息列表||null
     */
    List<ArticleInformation> sortArticleInformationList(List<ArticleInformation> articleInformationList, ArticleSort articleSort);

    /**
     * 获得与文章id同类的文章
     * @param id 需要查询的文章id
     * @return 文章列表||null
     */
    List<Article> getSameClassifyArticleList(long id);

    /**
     * 获取与该文章有相同标签的文章
     * @param id 需要查询的文章id
     * @return 文章列表||null
     */
    List<ArticleTagNominate> getMoreTagArticleList(long id);

    /**
     * 根据文章id判断是否存在
     * @param articleID 文章id
     * @return 存在为真，反之为假
     */
    boolean isExist(Long articleID);

    /**
     * 根据关键字从文章列表中筛选
     * 该方法要求传入的文章列表不可为null
     * 文章列表size为0时返回null
     * @param articleInformationList 文章列表
     * @param keyword 关键字
     * @return 文章列表||null
     */
    List<ArticleInformation> getArticleInformationListByKeyword(List<ArticleInformation> articleInformationList, String keyword);

    /**
     * 根据分类列表从文章列表中筛选
     * 该方法要求传入的文章列表不可为null
     * 文章列表size为0时返回null
     * @param articleInformationList 文章列表
     * @param classifyIDList 分类列表
     * @return 文章列表||null
     */
    List<ArticleInformation> getArticleInformationListByClassifyList(List<ArticleInformation> articleInformationList, List<Long> classifyIDList);

    /**
     * 根据标签列表从文章列表中筛选
     * 该方法要求传入的文章列表不可为null
     * 文章列表size为0时返回null
     * @param articleInformationList 文章列表
     * @param tagIDList 标签列表
     * @return 文章列表||null
     */
    List<ArticleInformation> getArticleInformationListByTagList(List<ArticleInformation> articleInformationList, List<Long> tagIDList);

    /**
     * 封装分页信息
     * @param articleInformationList 文章列表
     * @param currentPage 页数
     * @param pageSize 页容
     * @return 分页信息
     */
    Pagination Pagination(List<ArticleInformation> articleInformationList, int currentPage, int pageSize);
}
