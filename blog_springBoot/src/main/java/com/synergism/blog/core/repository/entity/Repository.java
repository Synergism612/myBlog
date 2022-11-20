package com.synergism.blog.core.repository.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 文件仓库表
 * </p>
 *
 * @author Synergism
 * @since 2022-11-14 09:38:10
 */
@Getter
@Setter
@TableName("repository")
@ApiModel(value = "Repository对象", description = "文件仓库表")
public class Repository {

    @ApiModelProperty("主键")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty("路径")
    @TableField("path")
    private String path;

    @ApiModelProperty("容量(MB)")
    @TableField("size")
    private Long size;

    @ApiModelProperty("已使用(MB)")
    @TableField("used")
    private double used;

    @ApiModelProperty("创建时间")
    @TableField("creation_time")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date creationTime;

    @ApiModelProperty("修改时间")
    @TableField(value = "modify_time", fill = FieldFill.INSERT_UPDATE)
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date modifyTime;

    public Repository(){}

    public Repository(String path, Long size) {
        this.path = path;
        this.size = size;
    }
}
