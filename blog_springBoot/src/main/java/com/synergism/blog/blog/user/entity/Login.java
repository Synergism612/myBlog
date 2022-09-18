package com.synergism.blog.user.entity;

import lombok.Getter;
import lombok.Setter;

import static com.synergism.blog.user.untils.LoginUtil.checkPassword;
import static com.synergism.blog.user.untils.LoginUtil.checkUsername;
import static com.synergism.blog.utils.StringUtil.checkStringIsEmpty;
import static com.synergism.blog.utils.StringUtil.checkStringIsUnsafe;
import static com.synergism.blog.utils.TypeUtil.asArray;

/**
 * 用户登录实体类
 */
@Getter
@Setter
public class Login {
    private String username = "";
    private String usernameName = "账号";
    private String password = "";
    private String passwordName = "密码";

    Login(){
    }

    Login(String username, String password) {
        //判空
        checkStringIsEmpty(asArray(username,password),getNames());
        //合法性检查
        checkStringIsUnsafe(username,password);
        //参数检查赋值
        setUsername(checkUsername(username));
        setPassword(checkPassword(password));
    }

    public String[] getCounts() {
        return new String[]{username, password};
    }

    public String[] getNames() {
        return new String[]{usernameName, passwordName};
    }

    @Override
    public String toString() {
        return "Login{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
