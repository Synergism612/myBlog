package com.synergism.blog.api.dbankAPI.service;

import com.synergism.blog.api.dbankAPI.entity.FolderForm;
import com.synergism.blog.core.repository.entity.FolderInformation;
import com.synergism.blog.result.Result;
import org.springframework.web.multipart.MultipartFile;

public interface DBankAPIService {

    /**
     * 获取用户仓库信息
     *
     * @param username 账号
     * @return 仓库信息
     */
    Result<FolderInformation> getRepository(String username);

    /**
     * 获取用户目录信息
     *
     * @param username 账号
     * @param path 路径
     * @return 目录信息
     */
    Result<FolderInformation> getFolder(String username, String path);

    /**
     * 保存文件
     *
     * @param username 账号
     * @param path     路径
     * @param file     文件
     * @return 成功
     */
    Result<String> saveFile(String username, String path, MultipartFile file);

    /**
     * 删除文件夹
     *
     * @param username 账号
     * @param folderID 文件夹id
     * @return 成功
     */
    Result<String> removeFolder(String username, Long folderID);

    /**
     * 删除文件
     *
     * @param username 账号
     * @param fileID   文件id
     * @return 成功
     */
    Result<String> removeFile(String username, Long fileID);

    /**
     * 创建文件夹
     * @param folderForm 文件夹信息
     * @return 成功
     */
    Result<String> saveFolder(FolderForm folderForm);

    /**
     * 自动保存文件
     * @param username 账号
     * @param file 文件
     * @param path 路径
     * @return href
     */
    Result<String> autoSaveFile(String username, MultipartFile file, String path);
}
