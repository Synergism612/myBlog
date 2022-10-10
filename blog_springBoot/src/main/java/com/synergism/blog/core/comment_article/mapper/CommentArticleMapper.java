package com.synergism.blog.core.comment_article.mapper;

import com.synergism.blog.core.comment_article.entity.CommentArticle;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 文章-评论对照表：文章的评论 Mapper 接口
 * </p>
 *
 * @author Synergism
 * @since 2022-10-10 09:54:07
 */
@Mapper
public interface CommentArticleMapper extends BaseMapper<CommentArticle> {

}
