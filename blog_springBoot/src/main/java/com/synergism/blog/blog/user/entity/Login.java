package com.synergism.blog.blog.user.entity;

import lombok.Getter;
import lombok.Setter;

import static com.synergism.blog.blog.user.untils.LoginUtil.checkPassword;
import static com.synergism.blog.blog.user.untils.LoginUtil.checkUsername;
import static com.synergism.blog.utils.StringUtil.checkStringIsEmpty;
import static com.synergism.blog.utils.StringUtil.checkStringIsUnsafe;
import static com.synergism.blog.utils.TypeUtil.asArray;

/**
 * 用户登录实体类
 */
@Getter
@Setter
public class Login {
    //账号
    private String username = "";
    //字段名
    private String usernameName = "账号";
    //密码
    private String password = "";
    //字段名
    private String passwordName = "密码";

    /**
     * 空参构造函数
     */
    Login(){
    }

    /**
     * 构造函数
     * @param username 账号
     * @param password 密码
     */
    Login(String username, String password) {
        //判空
        checkStringIsEmpty(asArray(username,password),getNames());
        //合法性检查
        checkStringIsUnsafe(username,password);
        //参数检查赋值
        setUsername(checkUsername(username));
        setPassword(checkPassword(password));
    }

    /**
     * 获取字段对应值
     * 账号值、密码值
     * @return 值数组
     */
    public String[] getCounts() {
        return new String[]{username, password};
    }

    /**
     * 获取字段名字
     * ”账号“、”密码“
     * @return 名字数组
     */
    public String[] getNames() {
        return new String[]{usernameName, passwordName};
    }
}
