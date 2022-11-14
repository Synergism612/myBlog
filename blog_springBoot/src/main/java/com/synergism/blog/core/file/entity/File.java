package com.synergism.blog.core.file.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.util.Date;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 文件表
 * </p>
 *
 * @author Synergism
 * @since 2022-11-14 09:39:02
 */
@Getter
@Setter
@TableName("file")
@ApiModel(value = "File对象", description = "文件表")
public class File {

    @ApiModelProperty("主键")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty("文件名")
    @TableField("name")
    private String name;

    @ApiModelProperty("文件类型")
    @TableField("type")
    private String type;

    @ApiModelProperty("文件大小(MB)")
    @TableField("size")
    private Long size;

    @ApiModelProperty("存储路径")
    @TableField("path")
    private String path;

    @ApiModelProperty("文件链接")
    @TableField("href")
    private String href;

    @ApiModelProperty("创建时间")
    @TableField("creation_time")
    private Date creationTime;

    @ApiModelProperty("修改时间")
    @TableField(value = "modify_time", fill = FieldFill.INSERT_UPDATE)
    private Date modifyTime;


}
