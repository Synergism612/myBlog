package com.synergism.blog.core.repository.service;

import com.synergism.blog.core.repository.entity.Repository;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 文件仓库表 服务类
 * </p>
 *
 * @author Synergism
 * @since 2022-11-14 09:38:10
 */
public interface RepositoryService extends IService<Repository> {

    /**
     * 保存文件仓库
     * @param userID 用户id
     * @param username 账号
     */
    void save(long userID,String username);
}
