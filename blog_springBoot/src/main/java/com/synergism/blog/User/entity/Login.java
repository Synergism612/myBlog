package com.synergism.blog.global.entity;

import lombok.Getter;
import lombok.Setter;

import static com.synergism.blog.global.utils.StringUtil.checkStringIsUnsafe;
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
        checkStringIsUnsafe(username); //检查合法性
        setUsername(username);
        setPassword(password);
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
