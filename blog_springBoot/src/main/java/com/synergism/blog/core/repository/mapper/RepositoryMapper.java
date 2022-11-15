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

    /**
     * 绑定文件仓库
     * @param repositoryID 文件仓库id
     * @param userID 用户id
     */
    void bundle(Long repositoryID, long userID);
}
