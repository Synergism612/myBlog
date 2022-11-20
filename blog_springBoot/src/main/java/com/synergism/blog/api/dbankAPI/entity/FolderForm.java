package com.synergism.blog.api.dbankAPI.entity;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.*;

/**
 * 新增评论接口类
 */
@Setter
@Getter
public class FolderForm {
    //账号
    @NotEmpty(message = "用户不存在")
    private String username;
    //名称
    @NotEmpty(message = "文件夹名不能为空")
    @Pattern(regexp = "^[^? \\ * | “ < > : /]{1,256}$", message = "文件夹名不合法")
    private String name;
    private String parentPath;

}
