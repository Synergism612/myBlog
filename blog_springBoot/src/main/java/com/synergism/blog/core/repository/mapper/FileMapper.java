package com.synergism.blog.core.repository.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.synergism.blog.core.repository.entity.File;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <p>
 * 文件表 Mapper 接口
 * </p>
 *
 * @author Synergism
 * @since 2022-11-14 09:39:02
 */
@Mapper
public interface FileMapper extends BaseMapper<File> {

    /**
     * 查询仓库下的文件
     * @param repositoryID 仓库id
     * @return 文件列表
     */
    List<File> selectListByRepositoryID(long repositoryID);

    /**
     * 查询文件夹下的文件
     * @param folderID 文件夹id
     * @return 文件列表
     */
    List<File> selectListByFolderID(long folderID);

    /**
     * 绑定仓库文件
     * @param fileID 文件id
     * @param repositoryID 文件仓库id
     */
    void bundleToRepository(long fileID, long repositoryID);

    /**
     * 绑定目录文件
     * @param fileID 文件id
     * @param folderID muluid
     */
    void bundleToFolder(long fileID, long folderID,long repositoryID);

    /**
     * 解绑文件
     * @param fileIDList 文章id列表
     */
    void unbundled(List<Long> fileIDList);

}
