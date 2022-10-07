package com.synergism.blog.security.authentication.entity;

import com.synergism.blog.core.role.entity.Role;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
public class Authentication {

    private String name;
    private int code;

    public Authentication(String name, int code) {
        this.name = name;
        this.code = code;
    }

    public static Authentication fromRole(Role role) {
        return new Authentication(role.getName(), role.getCode());
    }

    public static List<Authentication> fromRoleList(List<Role> roleList) {
       return roleList.stream().map(Authentication::fromRole).collect(Collectors.toList());
    }

    public static List<Authentication> init(){
        List<Authentication> result = new ArrayList<>();
        result.add(new Authentication("root",1));
        result.add(new Authentication("user",2));

        return result;
    }

    @Override
    public String toString() {
        return "Authentication{" +
                "name='" + name + '\'' +
                ", code=" + code +
                '}';
    }
}
