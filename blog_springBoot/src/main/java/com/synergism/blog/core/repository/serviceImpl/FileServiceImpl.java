package com.synergism.blog.core.repository.serviceImpl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.synergism.blog.core.repository.entity.File;
import com.synergism.blog.core.repository.entity.Repository;
import com.synergism.blog.core.repository.mapper.FileMapper;
import com.synergism.blog.core.repository.service.FileService;
import com.synergism.blog.exception.custom.IOErrorException;
import com.synergism.blog.exception.custom.IllegalRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

/**
 * <p>
 * 文件表 服务实现类
 * </p>
 *
 * @author Synergism
 * @since 2022-11-14 09:39:02
 */
@Service
public class FileServiceImpl extends ServiceImpl<FileMapper, File> implements FileService {

    private final String separator = java.io.File.separator;

//    private final String hrefBase = "http://139.198.106.228:9010" + separator;

    private final String hrefBase = "http://localhost:9010" + separator;

    private final FileMapper mapper;

    @Autowired
    public FileServiceImpl(FileMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public List<File> getListByRepositoryID(long repositoryID) {
        List<File> result = mapper.selectListByRepositoryID(repositoryID);
        return result.size() == 0 ? null : result;
    }

    @Override
    public List<File> getListByFolderID(long folderID) {
        List<File> result = mapper.selectListByFolderID(folderID);
        return result.size() == 0 ? null : result;
    }

    @Override
    @Transactional
    public void remove(List<Long> fileIDList) {
        mapper.unbundled(fileIDList);
        mapper.deleteBatchIds(fileIDList);
    }

    @Override
    @Transactional
    public String saveToFolder(Repository repository, long folderID, MultipartFile multipartFile, String resultPath) {
        String href = hrefBase + resultPath;
        File file = getFile(repository,multipartFile,resultPath,href);
        mapper.insert(file);
        mapper.bundleToFolder(file.getId(), folderID, repository.getId());
        return href;
    }

    @Override
    @Transactional
    public String saveToRepository(Repository repository, MultipartFile multipartFile, String resultPath) {
        String href = hrefBase + resultPath;
        File file = getFile(repository,multipartFile,resultPath,href);
        mapper.insert(file);
        mapper.bundleToRepository(file.getId(), repository.getId());
        return href;
    }

    private void checkRepositoryUsed(Repository repository, Double fileSizeMB) {
        double residue = repository.getSize() - repository.getUsed();
        if (residue < fileSizeMB) {
            throw new IOErrorException("云盘剩余容量(" + residue + ")不足");
        }
    }

    private void checkName(String filename){
        if (filename == null) {
            throw new IllegalRequestException("文件名称错误");
        }
    }

    private File getFile(Repository repository, MultipartFile file,String resultPath ,String href){
        double fileSizeMB = new BigDecimal(file.getSize() / 1024 / 1024).setScale(2, RoundingMode.HALF_UP).doubleValue();
        this.checkRepositoryUsed(repository,fileSizeMB);
        String filename = file.getOriginalFilename();
        this.checkName(filename);
        return new File(filename.substring(0, filename.indexOf('.')), filename.substring(filename.indexOf('.')), file.getContentType(), fileSizeMB, resultPath, href);
    }
}
