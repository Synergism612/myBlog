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
     * 查询该文章下的评论信息
     * @param articleID 文章id
     * @return 评论信息列表
     */
    List<CommentInformation> selectCommentInformationListByArticleID(long articleID);

    /**
     * 绑定新的评论
     * @param commentID 评论id
     * @param articleID 文章id
     * @param userID 用户id
     */
    void bundle(long commentID, long articleID, long userID);

    /**
     * 更新点赞数
     * @param commentID 评论id
     * @param number 点赞量
     */
    void updateLike(long commentID, long number);
}
