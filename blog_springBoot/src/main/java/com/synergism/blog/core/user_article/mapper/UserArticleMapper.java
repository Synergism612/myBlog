package com.synergism.blog.core.user_article.mapper;

import com.synergism.blog.core.user_article.entity.UserArticle;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 用户-文章对照表：用户的文章 Mapper 接口
 * </p>
 *
 * @author Synergism
 * @since 2022-10-09 04:43:45
 */
@Mapper
public interface UserArticleMapper extends BaseMapper<UserArticle> {

}
