package com.synergism.blog.core.user.serviceImpl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.synergism.blog.core.user.entity.Author;
import com.synergism.blog.core.user.entity.User;
import com.synergism.blog.core.user.mapper.UserMapper;
import com.synergism.blog.core.user.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author Synergism
 * @since 2022-08-25 04:30:53
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    private final UserMapper mapper;

    @Autowired
    public UserServiceImpl(UserMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public boolean isExist(String username) {
        return this.getOne(new LambdaQueryWrapper<User>().eq(User::getUsername, username)) != null;
    }

    @Override
    public long getID(String username) {
        User result = this.getOne(new LambdaQueryWrapper<User>().eq(User::getUsername, username));
        return result==null?-1: result.getId();
    }

    @Override
    public Author getAuthorByArticleID(long articleID) {
        Author result = mapper.selectAuthorByArticleID(articleID);
        result.replenish();
        return result;
    }

    @Override
    public Author getAuthorByUsername(String username) {
        Author result = mapper.selectAuthorByUsername(username);
        result.replenish();
        return result;
    }
}
