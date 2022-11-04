package com.synergism.blog.api.userAPI.untils;

/**
 * 登录工具类
 */
public class LoginUtil {
    /**
     * 检查账号格式
     * 不合格抛出异常
     * @param username 账号
     * @return 账号
     */
    public static String checkUsername(String username){
        if (username.indexOf("@")*username.indexOf(".com")<2)
            throw new IllegalArgumentException("格式错误");
        return username;
    }

    /**
     * 检查密码格式
     * 不合格抛出异常
     * @param password 密码
     * @return 密码
     */
    public static String checkPassword(String password){
        if (password.length()<8)
            throw new IllegalArgumentException("密码长度不足8位");
        return password;
    }
}
