package com.synergism.blog.core.user.service;

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
    boolean ifExist(String username);

    UserInformation getOneByArticleID(long articleID);

}
