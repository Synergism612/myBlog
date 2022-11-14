package com.synergism.blog.api.EmailAPI.service;

import com.synergism.blog.core.article.entity.Pagination;
import com.synergism.blog.core.article.enumeration.ArticleSort;
import com.synergism.blog.core.classify.entity.ClassifyInformation;
import com.synergism.blog.core.tag.entity.TagInformation;
import com.synergism.blog.core.user.entity.UserInformation;
import com.synergism.blog.result.Result;

import java.util.List;

public interface EmailAPIService {

    /**
     * 发送注册验证码邮箱
     * @param mail 目标
     * @param key 密钥
     * @return 成功
     */
    Result<String> getRegisterMailCode(String mail, String key);
}
