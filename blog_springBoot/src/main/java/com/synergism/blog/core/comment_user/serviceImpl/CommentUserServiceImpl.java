package com.synergism.blog.core.comment_user.serviceImpl;

import com.synergism.blog.core.comment_user.entity.CommentUser;
import com.synergism.blog.core.comment_user.mapper.CommentUserMapper;
import com.synergism.blog.core.comment_user.service.CommentUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户-评论对照表：用户的评论 服务实现类
 * </p>
 *
 * @author Synergism
 * @since 2022-10-10 09:54:21
 */
@Service
public class CommentUserServiceImpl extends ServiceImpl<CommentUserMapper, CommentUser> implements CommentUserService {

}
