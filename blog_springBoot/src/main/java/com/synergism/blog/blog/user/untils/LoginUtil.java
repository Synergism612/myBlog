package com.synergism.blog.blog.user.untils;

public class LoginUtil {
    public static String checkUsername(String username){
        if (username.indexOf("@")*username.indexOf(".com")<2)
            throw new IllegalArgumentException("格式错误");

        return username;
    }

    public static String checkPassword(String password){
        if (password.length()<8)
            throw new IllegalArgumentException("密码长度不足8位");
        return password;
    }
}
