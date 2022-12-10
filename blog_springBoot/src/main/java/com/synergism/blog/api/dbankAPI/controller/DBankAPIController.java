package com.synergism.blog.api.dbankAPI.controller;

import com.synergism.blog.api.dbankAPI.entity.FolderForm;
import com.synergism.blog.api.dbankAPI.service.DBankAPIService;
import com.synergism.blog.core.repository.entity.FolderInformation;
import com.synergism.blog.result.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * 共有接口控制器
 */
@RestController
@RequestMapping("/api/DBank")
public class DBankAPIController {

    DBankAPIService service;

    @Autowired
    DBankAPIController(DBankAPIService service){
        this.service = service;
    }

    /**
     * 获取仓库目录
     * @param username 账号
     * @return 目录结构
     */
    @Validated
    @GetMapping("repository")
    public Result<FolderInformation> repository(
            @RequestParam @NotEmpty(message = "用户不存在") String username
            ){
        return service.getRepository(username);
    }

    /**
     * 获取文件夹目录
     * @param username 账号
     * @param path 路径
     * @return 目录结构
     */
    @Validated
    @GetMapping("folder")
    public Result<FolderInformation> folder(
            @RequestParam @NotEmpty(message = "用户不存在") String username,
            @RequestParam @NotEmpty(message = "路径不存在") String path
            ){
        return service.getFolder(username,path);
    }

    /**
     * 上传文件接口
     * @param username 账号
     * @param path 路径
     * @param file 文件
     * @return 成功
     */
    @Validated
    @PostMapping("file")
    public Result<String> file(
            @RequestParam @NotEmpty(message = "用户不存在") String username,
            @RequestParam @NotEmpty(message = "路径不存在") String path,
            @RequestPart MultipartFile file
    ){
        return service.saveFile(username,path,file);
    }

    /**
     * 删除文件夹接口
     * @param username 账号
     * @param folderID 文件夹id
     * @return 成功
     */
    @Validated
    @DeleteMapping("folder")
    public Result<String> removeFolder(
            @RequestParam @NotEmpty(message = "用户不存在") String username,
            @RequestParam @NotNull(message = "文件夹不存在")
            @Min(value = 1, message = "文件夹不存在")  Long folderID
    ){
        return service.removeFolder(username,folderID);
    }

    /**
     * 删除文件接口
     * @param username 账号
     * @param fileID 文件id
     * @return 成功
     */
    @Validated
    @DeleteMapping("file")
    public Result<String> removeFile(
            @RequestParam @NotEmpty(message = "用户不存在") String username,
            @RequestParam @NotNull(message = "文件不存在")
            @Min(value = 1, message = "文件不存在")  Long fileID
    ){
        return service.removeFile(username,fileID);
    }

    /**
     * 新建文件夹接口
     * @param folderForm 文件夹信息表单
     * @return 成功
     */
    @PostMapping("folder")
    public Result<String> saveFolder(@RequestBody @Valid FolderForm folderForm){
        return service.saveFolder(folderForm);
    }
}
