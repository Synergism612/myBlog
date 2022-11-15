package com.synergism.blog.core.folder.mapper;

import com.synergism.blog.core.folder.entity.Folder;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <p>
 * 文件夹表 Mapper 接口
 * </p>
 *
 * @author Synergism
 * @since 2022-11-14 09:38:43
 */
@Mapper
public interface FolderMapper extends BaseMapper<Folder> {

    /**
     * 查询仓库下的文件夹
     * @param repositoryID 仓库id
     * @return 文件列表
     */
    List<Folder> selectListByRepositoryID(long repositoryID);

    /**
     * 查询文件夹下的文件夹
     * @param folderID 文件夹id
     * @return 文件列表
     */
    List<Folder> selectListByFolderID(long folderID);

    /**
     * 绑定文件夹
     * @param folderID 文件夹id
     * @param repositoryID 仓库id
     */
    void bundle(Long folderID, long repositoryID);

    /**
     * 解绑文件夹
     * @param folderID 文件夹id
     */
    void unbundled(long folderID);
}
