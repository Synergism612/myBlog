package com.synergism.blog.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Login {
    private String username;
    private String usernameName = "账号";
    private String password;
    private String passwordName = "密码";

    Login(String username, String password) {
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
