package com.synergism.blog.core.user.service;

import com.synergism.blog.core.user.entity.Author;
import com.synergism.blog.core.user.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;
import com.synergism.blog.core.user.entity.UserInformation;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Synergism
 * @since 2022-08-25 04:30:53
 */
public interface UserService extends IService<User> {

    /**
     * 是否存在
     * @param username 账号
     * @return 存在返回真，不存在返回否
     */
    boolean isExist(String username);

    long getID(String username);

    /**
     * 通过文章id获得文章作者信息
     * @param articleID 文章id
     * @return 作者信息
     */
    Author getAuthorByArticleID(long articleID);

}
