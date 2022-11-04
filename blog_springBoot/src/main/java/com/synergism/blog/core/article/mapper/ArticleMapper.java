package com.synergism.blog.core.article.mapper;

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
    List<ArticleInformation> selectArticleInformationByUsername(String username);


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

}
