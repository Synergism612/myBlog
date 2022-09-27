package com.synergism.blog.security.authentication.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.synergism.blog.security.authentication.entity.Role;
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
public interface RoleMapper extends BaseMapper<Role> {

}
