package com.synergism.blog.core.repository.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.synergism.blog.core.repository.entity.File;
import com.synergism.blog.core.repository.entity.Repository;
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
     * 删除文件
     * @param fileIDList 文件id列表
     */
    void remove(List<Long> fileIDList);

    /**
     * 保存目录下的文件
     * @param repository 仓库信息
     * @param folderID 文件夹id
     * @param multipartFile 文件
     * @param resultPath 结果路径
     * @return 返回代理网址
     */
    String saveToFolder(Repository repository,long folderID,MultipartFile multipartFile,String resultPath);

    /**
     * 保存目录下的文件
     * @param repository 仓库信息
     * @param multipartFile 文件
     * @param resultPath 结果路径
     * @return 返回代理网址
     */
    String saveToRepository(Repository repository, MultipartFile multipartFile,String resultPath);
}
