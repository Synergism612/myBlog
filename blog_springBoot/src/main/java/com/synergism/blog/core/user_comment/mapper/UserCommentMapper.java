package com.synergism.blog.core.user_comment.mapper;

import com.synergism.blog.core.user_comment.entity.UserComment;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 用户-评论对照表：用户的评论 Mapper 接口
 * </p>
 *
 * @author Synergism
 * @since 2022-10-11 05:47:20
 */
@Mapper
public interface UserCommentMapper extends BaseMapper<UserComment> {

}
