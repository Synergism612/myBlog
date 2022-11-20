package com.synergism.blog.core.repository.serviceImpl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.synergism.blog.core.repository.entity.Repository;
import com.synergism.blog.core.repository.entity.FolderInformation;
import com.synergism.blog.core.repository.mapper.RepositoryMapper;
import com.synergism.blog.core.repository.service.RepositoryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;

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

    //分隔符
    private final String separator = File.separator;

    private final RepositoryMapper mapper;

    @Autowired
    public RepositoryServiceImpl(RepositoryMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    @Transactional
    public void save(long userID, String username) {
        Repository repository = new Repository(username+"\\", 1024L);
        mapper.insert(repository);
        mapper.bundle(repository.getId(), userID);
    }

    @Override
    public FolderInformation getRepositoryInformation(String username) {
        return mapper.selectRepositoryInformationByUsername(username);
    }

    @Override
    public Repository getOne(String username) {
       return mapper.selectOne(new LambdaQueryWrapper<Repository>().eq(Repository::getPath, username + separator));
    }
}
