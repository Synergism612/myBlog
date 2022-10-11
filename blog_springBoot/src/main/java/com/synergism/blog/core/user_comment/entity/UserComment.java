package com.synergism.blog.core.user_comment.entity;

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
 * 用户-评论对照表：用户的评论
 * </p>
 *
 * @author Synergism
 * @since 2022-10-11 05:47:20
 */
@Getter
@Setter
@TableName("user_comment")
@ApiModel(value = "UserComment对象", description = "用户-评论对照表：用户的评论")
public class UserComment {

    @ApiModelProperty("主键")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty("用户主键")
    @TableField("user_id")
    private Long userId;

    @ApiModelProperty("评论主键")
    @TableField("comment_id")
    private Long commentId;


}
