package com.synergism.blog.api.comments.serviceImpl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.synergism.blog.api.comments.entity.Comments;
import com.synergism.blog.api.comments.entity.CommentsFamily;
import com.synergism.blog.api.comments.service.CommentsService;
import com.synergism.blog.api.user.entity.UserInformation;
import com.synergism.blog.core.comment.entity.Comment;
import com.synergism.blog.core.comment.service.CommentService;
import com.synergism.blog.core.user.entity.User;
import com.synergism.blog.core.user.service.UserService;
import com.synergism.blog.core.user_comment.entity.UserComment;
import com.synergism.blog.core.user_comment.service.UserCommentService;
import com.synergism.blog.utils.TypeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentsServiceImpl implements CommentsService {

    private final CommentService commentService;
    private final UserCommentService userCommentService;
    private final UserService userService;

    @Autowired
    public CommentsServiceImpl(CommentService commentService, UserCommentService userCommentService, UserService userService) {
        this.commentService = commentService;
        this.userCommentService = userCommentService;
        this.userService = userService;
    }

    @Override
    public List<Comments> getAllComments() {
        //获得所有评论
        List<Comment> commentList = commentService.list();
        //对照表
        List<UserComment> userCommentList = userCommentService.list(new LambdaQueryWrapper<UserComment>()
                .in(UserComment::getCommentId,commentList
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
        List<Comments> commentsList = userCommentList.stream().map(userComment -> {
            User user = null;
            for (User u : userList) {
               if( u.getId().equals(userComment.getUserId()))
                   user = u;
            }
            Comment comment = null;
            for (Comment c : commentList) {
                if (c.getId().equals(userComment.getCommentId()))
                    comment = c;
            }
            if (TypeUtil.ifNull(comment,user)){
                return null;
            }
            assert comment != null;
            assert user != null;
            return new Comments(comment, UserInformation.getInstance(user));
        }).collect(Collectors.toList());

        //分配父评论
        commentsList.forEach(comments -> {
            if (comments.getParentID()!=null){
                for (Comments c : commentsList) {
                    if (c.getId().equals(comments.getParentID())){
                        comments.setParent(new CommentsFamily(c));
                        c.putSon(new CommentsFamily(comments));
                    }
                }
            }
        });
        return commentsList;
    }

    @Override
    public List<Comments> getIndexComments() {
        List<Comments> result = this.getAllComments()
                .stream()
                .sorted(Comparator.comparing(Comments::getLikeCount).reversed())
                .collect(Collectors.toList());
        if (result.size()>5)
            return result.subList(0,5);
        return result;
    }
}
