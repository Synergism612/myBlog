package com.synergism.blog.core.user.service;

import com.synergism.blog.core.user.entity.Login;
import com.synergism.blog.core.user.entity.Register;
import com.synergism.blog.core.user.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;
import com.synergism.blog.core.user.entity.UserInformation;
import com.synergism.blog.result.entity.Result;

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
     * 登录服务
     *
     * @param login 登录信息
     * @return 结果[null]
     */
    Result<UserInformation> login(Login login);

    /**
     * 注册服务
     *
     * @param register 注册信息
     * @return 结果[null]
     */
    Result<String> register(Register register);

    /**
     * 是否存在
     * @param username 账号
     * @return 存在返回真，不存在返回否
     */
    boolean ifExist(String username);

}
