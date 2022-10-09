package com.synergism.blog.api.user.serviceImpl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.synergism.blog.api.user.entity.Login;
import com.synergism.blog.api.user.entity.Register;
import com.synergism.blog.api.user.entity.UserInformation;
import com.synergism.blog.api.user.service.UserApiService;
import com.synergism.blog.core.user.entity.User;
import com.synergism.blog.core.user.service.UserService;
import com.synergism.blog.result.entity.CodeMsg;
import com.synergism.blog.result.entity.Result;
import com.synergism.blog.utils.TypeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserApiServiceImpl implements UserApiService {

    private final UserService userService;

    @Autowired
    public UserApiServiceImpl(UserService userService) {
        this.userService = userService;
    }

    @Override
    public Result<UserInformation> login(Login login) {
        //获得对应用户
        User user = userService.getOne(new QueryWrapper<User>().eq("username", login.getUsername()));
        //对象判空
        TypeUtil.isNull(user);
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
        if (userService.ifExist(register.getUsername())) {
            return Result.error(CodeMsg.REGISTER_ERROR.fillArgs("账号已存在"));
        }
        //保存到数据库
        if (userService.save(User.getInstance(register)))
            return Result.success();
        else
            return Result.error(CodeMsg.REGISTER_ERROR.fillArgs("注册失败"));
    }

}
