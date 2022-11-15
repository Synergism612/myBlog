package com.synergism.blog.core.repository.serviceImpl;

import com.synergism.blog.core.repository.entity.Repository;
import com.synergism.blog.core.repository.mapper.RepositoryMapper;
import com.synergism.blog.core.repository.service.RepositoryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    private final RepositoryMapper mapper;

    @Autowired
    public RepositoryServiceImpl(RepositoryMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    @Transactional
    public void save(long userID, String username) {
        Repository repository = new Repository(username.substring(0, username.indexOf('@')), 1024L);
        mapper.insert(repository);
        mapper.bundle(repository.getId(), userID);
    }
}
