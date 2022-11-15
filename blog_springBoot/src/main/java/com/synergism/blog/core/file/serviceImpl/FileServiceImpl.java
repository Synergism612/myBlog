package com.synergism.blog.core.file.serviceImpl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.synergism.blog.core.file.entity.File;
import com.synergism.blog.core.file.mapper.FileMapper;
import com.synergism.blog.core.file.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>
 * 文件表 服务实现类
 * </p>
 *
 * @author Synergism
 * @since 2022-11-14 09:39:02
 */
@Service
public class FileServiceImpl extends ServiceImpl<FileMapper, File> implements FileService {

    private final FileMapper mapper;

    @Autowired
    public FileServiceImpl(FileMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public List<File> getListByRepositoryID(long repositoryID) {
        List<File> result = mapper.selectListByRepositoryID(repositoryID);
        return result.size() == 0 ? null : result;
    }

    @Override
    public List<File> getListByFolderID(long folderID) {
        List<File> result = mapper.selectListByFolderID(folderID);
        return result.size() == 0 ? null : result;
    }

    @Override
    @Transactional
    public void saveToRepository(long repositoryID, String name, String suffix, String type, double size, String path, String href) {
        File file = new File(name, suffix, type, size, path, href);
        mapper.insert(file);
        mapper.bundleToRepository(file.getId(), repositoryID);
    }

    @Override
    @Transactional
    public void saveToFolder(long folderID, String name, String suffix, String type, double size, String path, String href) {
        File file = new File(name, suffix, type, size, path, href);
        mapper.insert(file);
        mapper.bundleToFolder(file.getId(), folderID);
    }

    @Override
    @Transactional
    public void delete(List<Long> fileIDList) {
        mapper.unbundled(fileIDList);
        mapper.deleteBatchIds(fileIDList);
    }
}
