package com.synergism.blog.api.userAPI.service;

import com.synergism.blog.api.userAPI.entity.Login;
import com.synergism.blog.api.userAPI.entity.Register;
import com.synergism.blog.core.user.entity.UserInformation;
import com.synergism.blog.result.Result;

public interface UserAPIService {
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
