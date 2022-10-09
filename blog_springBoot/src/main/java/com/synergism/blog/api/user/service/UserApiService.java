package com.synergism.blog.api.user.service;

import com.synergism.blog.api.user.entity.Login;
import com.synergism.blog.api.user.entity.Register;
import com.synergism.blog.api.user.entity.UserInformation;
import com.synergism.blog.result.entity.Result;

public interface UserApiService {
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

}
