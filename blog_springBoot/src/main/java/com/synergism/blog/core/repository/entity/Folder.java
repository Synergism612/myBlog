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
 * 文件夹表
 * </p>
 *
 * @author Synergism
 * @since 2022-11-14 09:38:43
 */
@Getter
@Setter
@TableName("folder")
@ApiModel(value = "Folder对象", description = "文件夹表")
public class Folder {

    @ApiModelProperty("主键")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty("路径")
    @TableField("path")
    private String path;

    @ApiModelProperty("文件夹名")
    @TableField("name")
    private String name;

    @ApiModelProperty("父文件夹id")
    @TableField("parent_id")
    private Long parentId;

    @ApiModelProperty("创建时间")
    @TableField("creation_time")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date creationTime;

    @ApiModelProperty("修改时间")
    @TableField(value = "modify_time", fill = FieldFill.INSERT_UPDATE)
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date modifyTime;

    public Folder(){}

    public Folder(String path, String name, Long parentId) {
        this.path = path;
        this.name = name;
        this.parentId = parentId;
    }
}
