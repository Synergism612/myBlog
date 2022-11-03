package com.synergism.blog.core.comment.mapper;

import com.synergism.blog.core.comment.entity.Comment;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.synergism.blog.core.comment.entity.CommentInformation;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

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
    /**
     * 查询所有评论信息
     * @return 评论信息列表
     */
    List<CommentInformation> selectAllCommentInformationList();

    /**
     * 添加新的评论外键绑定
     * @param commentID 评论id
     * @param articleID 文章id
     * @param userID 用户id
     */
    void insertComment(long commentID, long articleID, long userID);
}
