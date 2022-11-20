package com.synergism.blog.api.dbankAPI.serviceImpl;

import com.synergism.blog.IO.service.IOService;
import com.synergism.blog.api.dbankAPI.entity.FolderForm;
import com.synergism.blog.api.dbankAPI.service.DBankAPIService;
import com.synergism.blog.core.repository.entity.File;
import com.synergism.blog.core.repository.entity.FolderInformation;
import com.synergism.blog.core.repository.entity.Repository;
import com.synergism.blog.core.repository.service.FileService;
import com.synergism.blog.core.repository.service.FolderService;
import com.synergism.blog.core.repository.service.RepositoryService;
import com.synergism.blog.core.user.service.UserService;
import com.synergism.blog.result.CodeMsg;
import com.synergism.blog.result.Result;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Collections;

@Service
public class DBankAPIServiceImpl implements DBankAPIService {
    private final UserService userService;
    private final RepositoryService repositoryService;
    private final FolderService folderService;
    private final FileService fileService;
    private final IOService ioService;


    public DBankAPIServiceImpl(UserService userService, RepositoryService repositoryService, FolderService folderService, FileService fileService, IOService ioService) {
        this.userService = userService;
        this.repositoryService = repositoryService;
        this.folderService = folderService;
        this.fileService = fileService;
        this.ioService = ioService;
    }


    @Override
    public Result<FolderInformation> getRepository(String username) {
        if (!userService.isExist(username)) {
            return Result.error(CodeMsg.BIND_ERROR.fillArgs("用户不存在"));
        }
        return Result.success(repositoryService.getRepositoryInformation(username));
    }

    @Override
    public Result<FolderInformation> getFolder(String username, String path) {
        long userID = userService.getID(username);
        if (userID == -1) {
            return Result.error(CodeMsg.BIND_ERROR.fillArgs("用户不存在"));
        }
        FolderInformation result = folderService.getFolderInformationByUserIDAndPath(userID, path);
        return result == null ? Result.error(CodeMsg.BIND_ERROR.fillArgs("文件夹不存在")) : Result.success(result);
    }

    @Override
    public Result<FolderInformation> saveFile(String username, String path, MultipartFile file) {
        Repository repository = repositoryService.getOne(username);
        String resultPath;
        if (repository == null) {
            return Result.error(CodeMsg.BIND_ERROR.fillArgs("仓库不存在"));
        }
        long repositoryID = repository.getId();
        resultPath = ioService.write(path, file);
        String[] nameArray = path.split("/|\\\\");
        try {
            if (nameArray.length < 2) {
                fileService.saveToRepository(repositoryID, file, resultPath);
            } else {
                long folderID = folderService.update(repository.getId(), path);
                fileService.saveToFolder(repositoryID,folderID, file, resultPath);
            }
        } catch (Exception e) {
            ioService.delete(resultPath);
            return Result.error(CodeMsg.FILE_ERROR.fillArgs("保存失败"));
        }
        return Result.success();
    }

    @Override
    public Result<String> removeFolder(String username, Long folderID) {
        long userID = userService.getID(username);
        if (userID == -1) {
            return Result.error(CodeMsg.BIND_ERROR.fillArgs("用户不存在"));
        }
        FolderInformation folderInformation = folderService.getFolderInformationByUserIDAndFolderID(userID,folderID);
        if (folderInformation==null){
            return Result.error(CodeMsg.FILE_ERROR.fillArgs("目录不存在"));
        }
        if (folderInformation.getFolderList().size()!=0||folderInformation.getFileList().size()!=0){
            return Result.error(CodeMsg.FILE_ERROR.fillArgs("目录不为空"));
        }
        folderService.remove(folderInformation.getId());
        ioService.delete(folderInformation.getPath());
        return Result.success();
    }

    @Override
    public Result<String> removeFile(String username, Long fileID) {
        long userID = userService.getID(username);
        if (userID == -1) {
            return Result.error(CodeMsg.BIND_ERROR.fillArgs("用户不存在"));
        }
        File file = fileService.getById(fileID);
        if (file==null){
            return Result.error(CodeMsg.FILE_ERROR.fillArgs("目录不存在"));
        }
        fileService.remove(Collections.singletonList(file.getId()));
        ioService.delete(file.getPath());
        return Result.success();
    }

    @Override
    public Result<String> saveFolder(FolderForm folderForm) {
        Repository repository = repositoryService.getOne(folderForm.getUsername());
        if (repository==null){
            return Result.error(CodeMsg.FILE_ERROR.fillArgs("仓库不存在"));
        }
        if (folderForm.getParentPath().isEmpty()){
            folderService.saveToRepository(repository.getId(), repository.getPath(), folderForm.getName());
            ioService.mkdir(repository.getPath(), folderForm.getName());
        }else{
            folderService.save(repository.getId(), folderForm.getParentPath(), folderForm.getName());
            ioService.mkdir(folderForm.getParentPath(), folderForm.getName());
        }
        return Result.success();
    }
}
