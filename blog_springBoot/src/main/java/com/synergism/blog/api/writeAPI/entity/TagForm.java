package com.synergism.blog.api.writeAPI.entity;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Setter
@Getter
public class TagForm {
    //账号
    @NotEmpty(message = "用户不存在")
    private String username;
    //名称
    @NotEmpty(message = "名称不能为空")
    @Size(max = 10,message = "名称长度不能超过10")
    private String name;
    //备注
    @NotEmpty(message = "备注不能为空")
    @Size(max = 20,message = "备注长度不能超过20")
    private String annotation;
}
