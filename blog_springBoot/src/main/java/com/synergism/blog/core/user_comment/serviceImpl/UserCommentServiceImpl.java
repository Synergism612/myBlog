package com.synergism.blog.core.user_comment.serviceImpl;

import com.synergism.blog.core.user_comment.entity.UserComment;
import com.synergism.blog.core.user_comment.mapper.UserCommentMapper;
import com.synergism.blog.core.user_comment.service.UserCommentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户-评论对照表：用户的评论 服务实现类
 * </p>
 *
 * @author Synergism
 * @since 2022-10-11 05:47:20
 */
@Service
public class UserCommentServiceImpl extends ServiceImpl<UserCommentMapper, UserComment> implements UserCommentService {

}
