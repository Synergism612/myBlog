package com.synergism.blog.core.folder.serviceImpl;

import com.synergism.blog.core.folder.entity.Folder;
import com.synergism.blog.core.folder.mapper.FolderMapper;
import com.synergism.blog.core.folder.service.FolderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    private final FolderMapper mapper;

    @Autowired
    public FolderServiceImpl(FolderMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public List<Folder> getListByRepositoryID(long repositoryID) {
        List<Folder> result = mapper.selectListByRepositoryID(repositoryID);
        return result.size()==0?null:result;
    }

    @Override
    public List<Folder> getListByFolderID(long folderID) {
        List<Folder> result = mapper.selectListByFolderID(folderID);
        return result.size()==0?null:result;
    }

    @Override
    @Transactional
    public void save(long repositoryID, String path, String name, Long parentID) {
        Folder folder = new Folder(path,name,parentID);
        mapper.insert(folder);
        mapper.bundle(folder.getId(), repositoryID);
    }

    @Override
    @Transactional
    public void delete(long folderID){
        mapper.unbundled(folderID);
        mapper.deleteById(folderID);
    }
}
