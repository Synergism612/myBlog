package com.synergism.blog.core.tag.mapper;

import com.synergism.blog.core.tag.entity.Tag;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 标签表 Mapper 接口
 * </p>
 *
 * @author Synergism
 * @since 2022-10-11 05:58:34
 */
@Mapper
public interface TagMapper extends BaseMapper<Tag> {

}
