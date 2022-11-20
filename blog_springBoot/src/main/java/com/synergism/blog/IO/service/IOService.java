package com.synergism.blog.IO.service;

import org.springframework.web.multipart.MultipartFile;

public interface IOService {

    /**
     * 写入文件
     * @param parentPath 父路径
     * @param file 文件
     * @return 成功为真，反之为否
     */
    String write(String parentPath, MultipartFile file);

    /**
     * 删除文件或文件夹
     * @param path 路径
     * @return 成功为真，反之为否
     */
    boolean delete(String path);

    /**
     * 创建文件夹
     * @param parentPath 父路径
     * @param name 文件夹名
     * @return 成功为真，反之为否
     */
    boolean mkdir(String parentPath,String name);

    /**
     * 创建文件夹
     * @param path 全路径
     * @return 成功为真，反之为否
     */
    boolean mkdir(String path);

    /**
     * 创库
     * @param username 账号
     * @return 成功为真，反之为否
     */
    boolean repository(String username);
}
