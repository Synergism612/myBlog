package com.synergism.blog.security.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
/**
 * 权限类
 */
public class Power {
    private String[] power;

    public Power(String[] power) {
        this.power = power;
    }

    /**
     * 基本权限方法
     */
    public static Power NOT_LOG_IN = new Power(new String[]{
            "/api/public/key",
            "/index",
            "/login",
            "/register"
    });
}
