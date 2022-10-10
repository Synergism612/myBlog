package com.synergism.blog.core.comment.entity;

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
 * 评论表
 * </p>
 *
 * @author Synergism
 * @since 2022-10-10 09:53:06
 */
@Getter
@Setter
@TableName("comment")
@ApiModel(value = "Comment对象", description = "评论表")
public class Comment {

    @ApiModelProperty("主键")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty("内容")
    @TableField("body")
    private String body;

    @ApiModelProperty("点赞数")
    @TableField("like_count")
    private String likeCount;

    @ApiModelProperty("父评论主键")
    @TableField("father_id")
    private Long fatherId;

    @ApiModelProperty("创建时间")
    @TableField("creation_time")
    private Date creationTime;

    @ApiModelProperty("修改时间")
    @TableField(value = "modify_time", fill = FieldFill.INSERT_UPDATE)
    private Date modifyTime;


}
