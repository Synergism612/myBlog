package com.synergism.blog.api.userAPI.serviceImpl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.synergism.blog.IO.service.IOService;
import com.synergism.blog.api.userAPI.entity.Login;
import com.synergism.blog.api.userAPI.entity.Register;
import com.synergism.blog.core.repository.service.RepositoryService;
import com.synergism.blog.core.user.entity.UserInformation;
import com.synergism.blog.api.userAPI.service.UserAPIService;
import com.synergism.blog.core.user.entity.User;
import com.synergism.blog.core.user.service.UserService;
import com.synergism.blog.result.CodeMsg;
import com.synergism.blog.result.Result;
import com.synergism.blog.utils.TypeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserAPIServiceImpl implements UserAPIService {

    private final UserService userService;
    private final RepositoryService repositoryService;
    private final IOService ioService;

    @Autowired
    public UserAPIServiceImpl(UserService userService, RepositoryService repositoryService, IOService ioService) {
        this.userService = userService;
        this.repositoryService = repositoryService;
        this.ioService = ioService;
    }

    @Override
    public Result<UserInformation> login(Login login) {
        //获得对应用户
        User user = userService.getOne(new LambdaQueryWrapper<User>().eq(User::getUsername, login.getUsername()));
        //对象判空
        if (TypeUtil.isNull(user)) {
            return Result.error(CodeMsg.USERNAME_ERROR);
        }
        //密码比对
        if (user.getPassword().equals(login.getPassword())) {
            //返回成功
            return Result.success(UserInformation.getInstance(user));
        }
        //返回失败
        return Result.error(CodeMsg.USERNAME_ERROR);
    }

    @Override
    public Result<String> register(Register register) {
        //账号是否已存在判断
        if (userService.isExist(register.getUsername())) {
            return Result.error(CodeMsg.REGISTER_ERROR.fillArgs("账号已存在"));
        }
        User user = User.getInstance(register);
        //保存到数据库
        if (userService.save(user)) {
            repositoryService.save(user.getId(), user.getUsername());
            ioService.repository(user.getUsername());
            return Result.success();
        }
        return Result.error(CodeMsg.REGISTER_ERROR.fillArgs("注册失败"));
    }

}
