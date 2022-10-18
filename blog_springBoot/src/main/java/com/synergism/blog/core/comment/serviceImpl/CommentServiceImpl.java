package com.synergism.blog.core.comment.serviceImpl;

import com.synergism.blog.api.userAPI.entity.UserInformation;
import com.synergism.blog.core.comment.entity.Comment;
import com.synergism.blog.core.comment.entity.CommentInformation;
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
import java.util.Objects;
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

}
