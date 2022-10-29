package com.synergism.blog.core.comment.mapper;

import com.synergism.blog.api.contentAPI.entity.AddComment;
import com.synergism.blog.core.comment.entity.Comment;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.synergism.blog.core.comment.entity.CommentInformation;
import com.synergism.blog.result.Result;
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
     * 通过文章id查询评论信息列表
     * @param articleID 文章id
     * @return 评论信息列比奥
     */
    List<CommentInformation> getAllCommentInformationListByArticleID(long articleID);

    void addComment(long commentID, long articleID, long userID);
}
