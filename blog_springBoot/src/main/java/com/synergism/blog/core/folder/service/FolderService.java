package com.synergism.blog.core.folder.service;

import com.synergism.blog.core.folder.entity.Folder;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 文件夹表 服务类
 * </p>
 *
 * @author Synergism
 * @since 2022-11-14 09:38:43
 */
public interface FolderService extends IService<Folder> {

    /**
     * 查询仓库下的文件夹
     * @param repositoryID 仓库id
     * @return 文件列表
     */
    List<Folder> getListByRepositoryID(long repositoryID);

    /**
     * 查询文件夹下的文件夹
     * @param folderID 文件夹id
     * @return 文件列表
     */
    List<Folder> getListByFolderID(long folderID);

    /**
     * 保存文件夹
     * @param repositoryID 仓库id
     * @param path 路径
     * @param name 文件夹名
     * @param parentID 父文件夹名 可为空
     */
    void save(long repositoryID,String path,String name,Long parentID);


    /**
     * 删除文件
     * @param folderID 文件夹id
     */
    void delete(long folderID);
}
