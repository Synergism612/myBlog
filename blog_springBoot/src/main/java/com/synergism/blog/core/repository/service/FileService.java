package com.synergism.blog.core.repository.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.synergism.blog.core.repository.entity.File;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 * 文件表 服务类
 * </p>
 *
 * @author Synergism
 * @since 2022-11-14 09:39:02
 */
public interface FileService extends IService<File> {

    /**
     * 查询仓库下的文件
     * @param repositoryID 仓库id
     * @return 文件列表
     */
    List<File> getListByRepositoryID(long repositoryID);

    /**
     * 查询文件夹下的文件
     * @param folderID 文件夹id
     * @return 文件列表
     */
    List<File> getListByFolderID(long folderID);

    /**
     * 保存仓库下的文件
     * @param repositoryID 文件仓库id
     * @param name 文件名
     * @param suffix 文件后缀
     * @param type 文件类型
     * @param size 文件大小
     * @param path 文件路径
     * @param href 文件链接
     */
    void saveToRepository(long repositoryID, String name,String suffix, String type, double size, String path, String href);

    /**
     * 保存目录下的文件
     * @param folderID 目录id
     * @param name 文件名
     * @param suffix 文件后缀
     * @param type 文件类型
     * @param size 文件大小
     * @param path 文件路径
     * @param href 文件链接
     */
    void saveToFolder(long folderID, String name,String suffix, String type, double size, String path, String href);

    /**
     * 删除文件
     * @param fileIDList 文件id列表
     */
    void delete(List<Long> fileIDList);

    /**
     * 保存目录下的文件
     * @param folderID 目录id
     * @param file 文件
     * @param resultPath 结果路径
     */
    void saveToFolder(long folderID, MultipartFile file, String resultPath);
}
