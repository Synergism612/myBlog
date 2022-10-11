package com.synergism.blog.core.article_classify.mapper;

import com.synergism.blog.core.article_classify.entity.ArticleClassify;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 分类-文章对照表：文章的分类 Mapper 接口
 * </p>
 *
 * @author Synergism
 * @since 2022-10-11 05:57:44
 */
@Mapper
public interface ArticleClassifyMapper extends BaseMapper<ArticleClassify> {

}
