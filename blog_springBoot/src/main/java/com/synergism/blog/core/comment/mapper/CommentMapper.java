package com.synergism.blog.core.comment.mapper;

import com.synergism.blog.core.comment.entity.Comment;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 评论表 Mapper 接口
 * </p>
 *
 * @author Synergism
 * @since 2022-10-10 09:53:06
 */
@Mapper
public interface CommentMapper extends BaseMapper<Comment> {

}
