package com.synergism.blog.User.serviceImpl;

import com.synergism.blog.User.entity.User;
import com.synergism.blog.User.mapper.UserMapper;
import com.synergism.blog.User.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Synergism
 * @since 2022-08-25 04:30:53
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

}
