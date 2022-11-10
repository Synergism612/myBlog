package com.synergism.blog.core.article.service;

import com.synergism.blog.core.article.entity.*;
import com.baomidou.mybatisplus.extension.service.IService;
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
     * 根据文章id列表判断是否都存在
     * @param articleIDList 文章id列表
     * @return 都存在为真，反之为假
     */
    boolean isExist(List<Long> articleIDList);

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

    /**
     * 获取分页信息
     * @param currentPage 页数
     * @param pageSize 页容
     * @param articleSort 排序
     * @param username 账号
     * @param keyword 关键字
     * @param classifyIDList 分类id列表
     * @param tagIDList 标签id列表
     * @return 分页信息
     */
    Pagination getPagination(int currentPage, int pageSize, ArticleSort articleSort, String username, String keyword, List<Long> classifyIDList, List<Long> tagIDList);

    /**
     * 保存新的文章
     * @param userID 用户id
     * @param icon 文章id
     * @param title 文章标题
     * @param body 文章内容
     * @param synopsis 文章摘要
     * @param ifPrivate 文章隐私设置
     * @param classifyID 分类id
     * @param tagIDList 标签id列表
     * @return 成功为真，反之为假
     */
    boolean save(long userID,String icon,String title,String body,String synopsis,int ifPrivate,Long classifyID,List<Long> tagIDList);

    /**
     * 删除文章列表
     * @param articleIDList 文章id列表
     * @param userID 用户id
     * @return 成功为真，反之为假
     */
    boolean remove(List<Long> articleIDList, long userID);

    /**
     * 查询对应文章信息
     * @param articleID 文章id
     * @return 文章信息
     */
    ArticleInformation getArticleInformationByID(long articleID);

    /**
     * 查询公开的归档信息
     * @return 归档信息列表
     */
    List<Archive> getPublicArchive();
    /**
     * 查询用户下的归档信息
     * @param userID 用户id
     * @return 归档信息列表
     */
    List<Archive> getArchiveByUserID(long userID);
}
