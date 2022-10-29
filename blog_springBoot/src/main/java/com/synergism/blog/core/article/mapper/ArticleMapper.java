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
     * 获取全部文章信息
     * @return 文章信息列表
     */
    List<ArticleInformation> getAllArticleInformationList();

    /**
     * 查询与文章id对应文章相同分类的文章
     * @param id 文章id
     * @return 文章列表
     */
    List<Article> getOneClassifyArticleList(long id);

    /**
     * 查询与文章id对应的文章有相同标签的文章
     * @param id 文章id
     * @return 文章标签推荐列表
     */
    List<ArticleTagNominate> getMoreTagArticleList(long id);
}
