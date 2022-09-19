package com.synergism.blog.blog.user.serviceImpl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.synergism.blog.blog.user.entity.User;
import com.synergism.blog.blog.user.mapper.UserMapper;
import com.synergism.blog.blog.user.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.synergism.blog.utils.TypeUtil;
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
        return this.getOne(new QueryWrapper<User>().eq("username", username))!=null;
    }
}
