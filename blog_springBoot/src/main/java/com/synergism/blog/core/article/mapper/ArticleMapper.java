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

    List<ArticleInformation> getAllArticleInformationList();

    List<Article> getOneClassifyArticleList(long id);

    List<ArticleTagNominate> getMoreTagArticleList(long id);
}
