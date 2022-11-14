package com.synergism.blog.core.repository.mapper;

import com.synergism.blog.core.repository.entity.Repository;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 文件仓库表 Mapper 接口
 * </p>
 *
 * @author Synergism
 * @since 2022-11-14 09:38:10
 */
@Mapper
public interface RepositoryMapper extends BaseMapper<Repository> {

}
