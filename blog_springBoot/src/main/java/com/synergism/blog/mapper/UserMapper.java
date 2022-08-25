package com.synergism.blog.mapper;

import com.synergism.blog.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Synergism
 * @since 2022-08-25 04:30:53
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

}
