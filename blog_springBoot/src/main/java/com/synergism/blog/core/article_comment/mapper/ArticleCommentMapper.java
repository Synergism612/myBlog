package com.synergism.blog.core.article_comment.mapper;

import com.synergism.blog.core.article_comment.entity.ArticleComment;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 文章-评论对照表：文章的评论 Mapper 接口
 * </p>
 *
 * @author Synergism
 * @since 2022-10-11 05:47:47
 */
@Mapper
public interface ArticleCommentMapper extends BaseMapper<ArticleComment> {

}
