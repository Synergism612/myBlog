package com.synergism.blog.core.comment_user.mapper;

import com.synergism.blog.core.comment_user.entity.CommentUser;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 用户-评论对照表：用户的评论 Mapper 接口
 * </p>
 *
 * @author Synergism
 * @since 2022-10-10 09:54:21
 */
@Mapper
public interface CommentUserMapper extends BaseMapper<CommentUser> {

}
