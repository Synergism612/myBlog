package com.synergism.blog.api.pigeonholeAPI.serviceImpl;

import com.synergism.blog.api.pigeonholeAPI.service.PigeonholeAPIService;
import com.synergism.blog.core.article.entity.Archive;
import com.synergism.blog.core.article.service.ArticleService;
import com.synergism.blog.core.user.service.UserService;
import com.synergism.blog.result.CodeMsg;
import com.synergism.blog.result.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class PigeonholeAPIServiceImpl implements PigeonholeAPIService {
    private final UserService userService;

    private final ArticleService articleService;

    @Autowired
    public PigeonholeAPIServiceImpl(UserService userService, ArticleService articleService) {
        this.userService = userService;
        this.articleService = articleService;
    }

    @Override
    public Result<List<Archive>> getArchive(String username) {
        if (username.isEmpty()) {
            return Result.success(articleService.getPublicArchive());
        } else {
            long userID = userService.getID(username);
            if (userID != -1) {
                return Result.success(articleService.getArchiveByUserID(userID));
            } else {
                return Result.error(CodeMsg.BIND_ERROR.fillArgs("用户不存在"));
            }
        }
    }
}
