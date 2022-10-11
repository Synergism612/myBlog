package com.synergism.blog.core.article_tag.mapper;

import com.synergism.blog.core.article_tag.entity.ArticleTag;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 文章-标签对照表：文章的标签 Mapper 接口
 * </p>
 *
 * @author Synergism
 * @since 2022-10-11 05:58:21
 */
@Mapper
public interface ArticleTagMapper extends BaseMapper<ArticleTag> {

}
