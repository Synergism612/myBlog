package com.synergism.blog.api.homepageAPI.entity;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.*;

@Getter
@Setter
public class FavoriteForm {
    //账号
    @NotNull(message = "账号不能为空")
    private String username;
    //主键
    private Long id;
    //名称
    @NotEmpty(message = "名称不能为空")
    @Size(max = 10,message = "名称长度不能超过10")
    private String name;
    //注释
    @NotEmpty(message = "备注不能为空")
    @Size(max = 20,message = "备注长度不能超过20")
    private String annotation;
    //隐私设置  0-公开 1-私有
    @NotNull(message = "隐私设置不能为空")
    @Min(value = 0,message = "隐私设置输入错误")
    @Max(value = 1,message = "隐私设置输入错误")
    private Integer ifPrivate;
}
