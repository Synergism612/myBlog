package com.synergism.blog.api.writeAPI.entity;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.*;
import java.util.List;

@Setter
@Getter
public class ArticleForm {

    //账号
    @NotEmpty(message = "账号不存在")
    private String username;
    //文章主键
    private Long id;
    //封面
    @NotEmpty(message = "封面不能为空")
    @Pattern(regexp = "/^https?:\\/\\/(.+\\/)+.+(\\.(gif|png|jpg|jpeg|webp|svg|psd|bmp|tif))$/i",message = "封面必须是图片链接")
    private String icon;
    //标题
    @NotEmpty(message = "标题不能为空")
    @Size(max = 10,message = "标题长度不超过10")
    private String title;
    //内容
    @NotEmpty(message = "正文不能为空")
    @Size(min = 200,message = "正文长度不小于200")
    private String body;
    //摘要
    @Size(max = 100,message = "摘要长度不超过100")
    private String synopsis;
    //隐私设置
    @NotNull(message = "隐私设置不能为空")
    @Min(value = 0,message = "隐私设置输入错误")
    @Max(value = 1,message = "隐私设置输入错误")
    private Integer ifPrivate;
    //分类id
    @NotNull(message = "文章需要一个分类")
    @Min(value = 1,message = "分类不存在")
    private Long classifyID;
    //标签id列表
    @NotNull(message = "文章需要至少一个标签")
    @Min(value = 1,message = "标签不存在")
    private List<Long> tagIDList;
}
