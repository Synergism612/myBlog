package com.synergism.blog.api.enshrineAPI.entity;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * 添加收藏接口类
 */
@Setter
@Getter
public class CollectionForm {
    //账号
    @NotEmpty(message = "账号不能为空")
    private String username;
    //标题
    @NotEmpty(message = "标题不能为空")
    @Size(max = 10,message = "标题长度不能超过10")
    private String title;
    //链接
    @NotEmpty(message = "链接不能为空")
    @Size(max = 200,message = "链接长度不能超过200")
    private String href;
    //摘要
    @NotEmpty(message = "摘要不能为空")
    @Size(max = 10,message = "摘要长度不能超过100")
    private String synopsis;
    //收藏夹id
    @NotNull(message = "收藏夹不存在")
    @Min(value = 1,message = "收藏夹不存在")
    private Long favoriteID;
}
