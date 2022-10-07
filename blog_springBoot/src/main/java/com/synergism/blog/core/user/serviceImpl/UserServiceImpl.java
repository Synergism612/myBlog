package com.synergism.blog.core.user.serviceImpl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.synergism.blog.core.user.entity.Login;
import com.synergism.blog.core.user.entity.Register;
import com.synergism.blog.core.user.entity.User;
import com.synergism.blog.core.user.entity.UserInformation;
import com.synergism.blog.core.user.mapper.UserMapper;
import com.synergism.blog.core.user.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.synergism.blog.result.entity.CodeMsg;
import com.synergism.blog.result.entity.Result;
import com.synergism.blog.utils.TypeUtil;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Synergism
 * @since 2022-08-25 04:30:53
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Override
    public Result<UserInformation> login(Login login) {
        //获得对应用户
        User user = this.getOne(new QueryWrapper<User>().eq("username", login.getUsername()));
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
        if (this.ifExist(register.getUsername())) {
            return Result.error(CodeMsg.REGISTER_ERROR.fillArgs("账号已存在"));
        }
        //保存到数据库
        if (this.save(User.getInstance(register)))
            return Result.success();
        else
            return Result.error(CodeMsg.REGISTER_ERROR.fillArgs("注册失败"));
    }

    @Override
    public boolean ifExist(String username) {
        return this.getOne(new QueryWrapper<User>().eq("username", username))!=null;
    }
}
