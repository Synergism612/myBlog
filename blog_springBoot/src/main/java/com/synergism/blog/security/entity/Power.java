package com.synergism.blog.security.entity;

import lombok.Getter;
import lombok.Setter;

/**
 * 权限类
 */
@Getter
@Setter
public class Power {
    private String[] power;

    public Power(String... powers) {
        this.power = powers;
    }

    /**
     * 基本权限方法
     */
    public static Power NOT_LOG_IN = new Power(
            "/api/public/key",
            "/api/public/error",
            "/api/mail/code",
            "/index",
            "/api/blog/user/login",
            "/api/blog/user/register");
}
