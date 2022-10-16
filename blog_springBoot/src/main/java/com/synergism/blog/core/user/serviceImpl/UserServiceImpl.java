package com.synergism.blog.core.user.serviceImpl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.synergism.blog.core.user.entity.User;
import com.synergism.blog.core.user.mapper.UserMapper;
import com.synergism.blog.core.user.service.UserService;
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

    @Override
    public boolean ifExist(String username) {
        return this.getOne(new LambdaQueryWrapper<User>().eq(User::getUsername, username)) != null;
    }
}
