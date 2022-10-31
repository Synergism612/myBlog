package com.synergism.blog.core.collection.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 收藏信息表
 * </p>
 *
 * @author Synergism
 * @since 2022-10-31 09:33:18
 */
@Getter
@Setter
@TableName("collection")
@ApiModel(value = "Collection对象", description = "收藏信息表")
public class Collection {

    @ApiModelProperty("主键")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty("标题")
    @TableField("title")
    private String title;

    @ApiModelProperty("网址")
    @TableField("href")
    private String href;

    @ApiModelProperty("摘要")
    @TableField("synopsis")
    private String synopsis;

    public Collection(){}

    public Collection(String title,String href,String synopsis){
        this.title = title;
        this.href = href;
        this.synopsis = synopsis;
    }

}
