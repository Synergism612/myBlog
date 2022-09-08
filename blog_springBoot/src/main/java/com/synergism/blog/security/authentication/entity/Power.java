package com.synergism.blog.security.authentication.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Power {
    private String[] power;

    public Power(String[] power) {
        this.power = power;
    }

    public static Power NOT_LOG_IN = new Power(new String[]{
            "/index",
            "/login",
            "/register"
    });
}
