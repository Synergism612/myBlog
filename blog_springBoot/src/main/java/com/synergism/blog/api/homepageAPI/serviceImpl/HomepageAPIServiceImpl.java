package com.synergism.blog.api.homepageAPI.serviceImpl;

import com.synergism.blog.api.homepageAPI.service.HomepageAPIService;
import com.synergism.blog.core.user.entity.Author;
import com.synergism.blog.core.user.service.UserService;
import com.synergism.blog.result.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HomepageAPIServiceImpl implements HomepageAPIService {

    private final UserService userService;

    @Autowired
    public HomepageAPIServiceImpl(UserService userService) {
        this.userService = userService;
    }

    @Override
    public Result<Author> getAuthor(String username) {
       return Result.success(userService.getAuthorByUsername(username));
    }
}
