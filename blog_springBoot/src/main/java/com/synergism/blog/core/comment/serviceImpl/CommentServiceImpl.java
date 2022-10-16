package com.synergism.blog.core.comment.serviceImpl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.synergism.blog.api.user.entity.UserInformation;
import com.synergism.blog.core.comment.entity.Comment;
import com.synergism.blog.core.comment.entity.CommentInformation;
import com.synergism.blog.core.comment.entity.CommentsFamily;
import com.synergism.blog.core.comment.mapper.CommentMapper;
import com.synergism.blog.core.comment.service.CommentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.synergism.blog.core.user.entity.User;
import com.synergism.blog.core.user.service.UserService;
import com.synergism.blog.core.user_comment.entity.UserComment;
import com.synergism.blog.core.user_comment.service.UserCommentService;
import com.synergism.blog.utils.TypeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 评论表 服务实现类
 * </p>
 *
 * @author Synergism
 * @since 2022-10-10 09:53:06
 */
@Service
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment> implements CommentService {

    private final UserCommentService userCommentService;
    private final UserService userService;

    @Autowired
    public CommentServiceImpl(UserCommentService userCommentService, UserService userService) {
        this.userCommentService = userCommentService;
        this.userService = userService;
    }

    @Override
    public List<CommentInformation> getAllComment() {
        //获得所有评论
        List<Comment> commentList = this.list();
        //对照表
        List<UserComment> userCommentList = userCommentService.list(new LambdaQueryWrapper<UserComment>()
                .in(UserComment::getCommentId, commentList
                        .stream()
                        .map(Comment::getId)
                        .collect(Collectors.toList())));
        //获得对应的用户id
        List<Long> userIDList = userCommentList.stream()
                .map(UserComment::getUserId)
                .distinct()
                .collect(Collectors.toList());
        //获取对应用户
        List<User> userList = userService.listByIds(userIDList);

        //分配用户
        List<CommentInformation> commentInformationList = userCommentList.stream().map(userComment -> {
            User user = null;
            for (User u : userList) {
                if (u.getId().equals(userComment.getUserId()))
                    user = u;
            }
            Comment comment = null;
            for (Comment c : commentList) {
                if (c.getId().equals(userComment.getCommentId()))
                    comment = c;
            }
            if (TypeUtil.ifNull(comment, user)) {
                return null;
            }
            assert comment != null;
            assert user != null;
            return new CommentInformation(comment, UserInformation.getInstance(user));
        }).collect(Collectors.toList());

        //分配父评论
        commentInformationList.forEach(comments -> {
            if (comments.getParentID() != null) {
                for (CommentInformation c : commentInformationList) {
                    if (c.getId().equals(comments.getParentID())) {
                        comments.setParent(new CommentsFamily(c));
                        c.putSon(new CommentsFamily(comments));
                    }
                }
            }
        });
        return commentInformationList;
    }

}
