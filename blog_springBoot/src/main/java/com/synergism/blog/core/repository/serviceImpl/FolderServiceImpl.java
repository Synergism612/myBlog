package com.synergism.blog.core.repository.serviceImpl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.synergism.blog.core.repository.entity.Folder;
import com.synergism.blog.core.repository.entity.FolderInformation;
import com.synergism.blog.core.repository.mapper.FolderMapper;
import com.synergism.blog.core.repository.service.FolderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.synergism.blog.exception.custom.IllegalRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.util.List;

/**
 * <p>
 * 文件夹表 服务实现类
 * </p>
 *
 * @author Synergism
 * @since 2022-11-14 09:38:43
 */
@Service
public class FolderServiceImpl extends ServiceImpl<FolderMapper, Folder> implements FolderService {

    //分隔符
    private final String separator = File.separator;

    private final FolderMapper mapper;

    @Autowired
    public FolderServiceImpl(FolderMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public List<Folder> getListByRepositoryID(long repositoryID) {
        List<Folder> result = mapper.selectListByRepositoryID(repositoryID);
        return result.size() == 0 ? null : result;
    }

    @Override
    public List<Folder> getListByFolderID(long folderID) {
        List<Folder> result = mapper.selectListByFolderID(folderID);
        return result.size() == 0 ? null : result;
    }

    @Override
    @Transactional
    public void save(long repositoryID, String path, String name, Long parentID) {
        Folder folder = new Folder(path, name, parentID);
        mapper.insert(folder);
        mapper.bundle(folder.getId(), repositoryID);
    }

    @Override
    @Transactional
    public void save(long repositoryID, String parentPath, String name) {
        long parentID = mapper.selectOne(new LambdaQueryWrapper<Folder>().eq(Folder::getPath, parentPath)).getId();
        parentPath += separator + name;
        Folder folder = new Folder(parentPath, name, parentID);
        mapper.insert(folder);
        mapper.bundle(folder.getId(), repositoryID);
    }

    @Override
    public void saveToRepository(long repositoryID, String parentPath, String name) {
        parentPath += separator + name;
        Folder folder = new Folder(parentPath, name, null);
        mapper.insert(folder);
        mapper.bundle(folder.getId(), repositoryID);
    }

    @Override
    @Transactional
    public void remove(long folderID) {
        mapper.unbundled(folderID);
        mapper.deleteById(folderID);
    }

    @Override
    @Transactional
    public long update(long repositoryID, String path) {
        String[] nameArray = path.split("/|\\\\");
        Folder folder = null;
        if (nameArray.length < 2) {
            folder = mapper.selectOne(new LambdaQueryWrapper<Folder>().eq(Folder::getPath, path));
            if (folder == null) {
                throw new IllegalRequestException("地址错误");
            }
        } else {
            String thePath = "";
            for (int i = 0; i < nameArray.length; i++) {
                String name = nameArray[i];
                if (i < 1) {
                    thePath += name;
                    continue;
                }
                thePath += separator + name;
                Folder newFolder = mapper.selectOne(new LambdaQueryWrapper<Folder>().eq(Folder::getPath, thePath));
                if (newFolder == null) {
                    this.save(repositoryID, thePath, thePath.substring(thePath.lastIndexOf(separator) + 1), folder == null ? null : folder.getId());
                    folder = mapper.selectOne(new LambdaQueryWrapper<Folder>().eq(Folder::getPath, thePath));
                } else {
                    folder = newFolder;
                }
            }
        }
        assert folder != null;
        return folder.getId();
    }

    @Override
    public FolderInformation getFolderInformationByUserIDAndFolderID(long userID, long folderID) {
        return mapper.selectFolderInformationByUserIDAndFolderID(userID, folderID);
    }

    @Override
    public FolderInformation getFolderInformationByUserIDAndPath(long userID, String path) {
        return mapper.selectFolderInformationByUserIDAndPath(userID, path);
    }
}
