package com.synergism.blog.core.repository.serviceImpl;

import com.synergism.blog.core.repository.entity.Repository;
import com.synergism.blog.core.repository.mapper.RepositoryMapper;
import com.synergism.blog.core.repository.service.RepositoryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 文件仓库表 服务实现类
 * </p>
 *
 * @author Synergism
 * @since 2022-11-14 09:38:10
 */
@Service
public class RepositoryServiceImpl extends ServiceImpl<RepositoryMapper, Repository> implements RepositoryService {

}
