package com.synergism.blog.core.article.mapper;

import com.synergism.blog.core.article.entity.Archive;
import com.synergism.blog.core.article.entity.Article;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.synergism.blog.core.article.entity.ArticleInformation;
import com.synergism.blog.core.article.entity.ArticleTagNominate;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <p>
 * 文章表 Mapper 接口
 * </p>
 *
 * @author Synergism
 * @since 2022-10-07 10:07:37
 */
@Mapper
public interface ArticleMapper extends BaseMapper<Article> {

    /**
     * 查询公开的所有文章
     * @return 文章信息列表
     */
    List<ArticleInformation> selectPublicArticleInformationList();


    /**
     * 查询用户下的所有文章
     * @param username 账号
     * @return 文章信息列表
     */
    List<ArticleInformation> selectArticleInformationListByUsername(String username);


    /**
     * 查询与文章id对应文章相同分类的文章
     * @param id 文章id
     * @return 文章列表
     */
    List<Article> selectSameClassifyArticleList(long id);

    /**
     * 查询与文章id对应的文章有相同标签的文章
     * @param id 文章id
     * @return 文章标签推荐列表
     */
    List<ArticleTagNominate> selectMoreTagArticleList(long id);

    /**
     * 绑定新的文章
     * @param articleID 文章id
     * @param userID 用户id
     */
    void bundle(long articleID, long userID, long classifyID, List<Long> tagIDList);

    /**
     * 解绑文章
     * @param articleIDList 文章id列表
     * @param userID 用户id
     */
    void unbundled(List<Long> articleIDList, long userID);

    /**
     * 查询对应的文章信息
     * 具有唯一性
     * @param articleID 文章id
     * @return 文章信息
     */
    ArticleInformation selectArticleInformationByArticleID(long articleID);

    /**
     * 查询用户下的档案信息
     * @param userID 用户id
     * @return 档案
     */
    List<Archive> selectArchiveByUserID(long userID);

    /**
     * 查询公共档案信息
     * @return 档案
     */
    List<Archive> selectPublicArchive();

    /**
     * 查询对应用户下的对应文章
     * @param username 账号
     * @param articleID 文章id
     * @return 文章信息
     */
    Article selectOneByUsernameAndArticleID(String username, long articleID);

    /**
     * 更新阅读量
     * @param articleID 文章id
     * @param number 数量
     */
    void updateViews(long articleID, long number);

    /**
     * 更新点赞量
     * @param articleID 文章id
     * @param number 数量
     */
    void updateLike(long articleID, long number);
}
