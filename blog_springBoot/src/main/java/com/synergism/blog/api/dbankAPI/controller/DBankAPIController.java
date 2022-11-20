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

    @Validated
    @GetMapping("repository")
    public Result<FolderInformation> repository(
            @RequestParam @NotEmpty(message = "用户不存在") String username
            ){
        return service.getRepository(username);
    }

    @Validated
    @GetMapping("folder")
    public Result<FolderInformation> folder(
            @RequestParam @NotEmpty(message = "用户不存在") String username,
            @RequestParam @NotEmpty(message = "路径不存在") String path
            ){
        return service.getFolder(username,path);
    }

    @Validated
    @PostMapping("file")
    public Result<FolderInformation> file(
            @RequestParam @NotEmpty(message = "用户不存在") String username,
            @RequestParam @NotEmpty(message = "路径不存在") String path,
            @RequestPart MultipartFile file
    ){
        return service.saveFile(username,path,file);
    }

    @Validated
    @DeleteMapping("folder")
    public Result<String> removeFolder(
            @RequestParam @NotEmpty(message = "用户不存在") String username,
            @RequestParam @NotNull(message = "文件夹不存在")
            @Min(value = 1, message = "文件夹不存在")  Long folderID
    ){
        return service.removeFolder(username,folderID);
    }

    @Validated
    @DeleteMapping("file")
    public Result<String> removeFile(
            @RequestParam @NotEmpty(message = "用户不存在") String username,
            @RequestParam @NotNull(message = "文件不存在")
            @Min(value = 1, message = "文件不存在")  Long fileID
    ){
        return service.removeFile(username,fileID);
    }

    @PostMapping("folder")
    public Result<String> saveFolder(@RequestBody @Valid FolderForm folderForm){
        return service.saveFolder(folderForm);
    }
}
