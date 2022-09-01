package com.synergism.blog.entity;

import com.synergism.blog.util.CheckStringUtil;
import lombok.Getter;
import lombok.Setter;

import static com.synergism.blog.util.CheckStringUtil.checkStringentUnsafe;

@Getter
@Setter
public class Login {
    private String username;
    private String usernameName = "账号";
    private String password;
    private String passwordName = "密码";

    Login(String username, String password) {
        checkStringentUnsafe(username); //检查合法性
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
