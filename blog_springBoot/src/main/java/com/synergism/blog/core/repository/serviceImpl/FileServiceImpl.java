package com.synergism.blog.core.repository.serviceImpl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.synergism.blog.core.repository.entity.File;
import com.synergism.blog.core.repository.mapper.FileMapper;
import com.synergism.blog.core.repository.service.FileService;
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

    private final String hrefBase = "http://http://139.198.106.228:9010" + separator;

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
    public void saveToRepository(long repositoryID, String name, String suffix, String type, double size, String path, String href) {
        File file = new File(name, suffix, type, size, path, href);
        mapper.insert(file);
        mapper.bundleToRepository(file.getId(), repositoryID);
    }

    @Override
    @Transactional
    public void saveToFolder(long repositoryID, long folderID, String name, String suffix, String type, double size, String path, String href) {
        File file = new File(name, suffix, type, size, path, href);
        mapper.insert(file);
        mapper.bundleToFolder(file.getId(), folderID, repositoryID);
    }

    @Override
    @Transactional
    public void remove(List<Long> fileIDList) {
        mapper.unbundled(fileIDList);
        mapper.deleteBatchIds(fileIDList);
    }

    @Override
    public String saveToFolder(long repositoryID, long folderID, MultipartFile file, String resultPath) {
        String name = file.getOriginalFilename();
        if (name == null) {
            throw new IllegalRequestException("文件名称错误");
        }
        String href = hrefBase + resultPath;
        double fileSizeMB = new BigDecimal(file.getSize() / 1024 / 1024).setScale(2, RoundingMode.HALF_UP).doubleValue();
        this.saveToFolder(repositoryID, folderID, name.substring(0, name.indexOf('.')), name.substring(name.indexOf('.')), file.getContentType(), fileSizeMB, resultPath, href);
        return href;
    }

    @Override
    public String saveToRepository(long repositoryID, MultipartFile file, String resultPath) {
        String name = file.getOriginalFilename();
        if (name == null) {
            throw new IllegalRequestException("文件名称错误");
        }
        String href = hrefBase + resultPath;
        double fileSizeMB = new BigDecimal(file.getSize() / 1024 / 1024).setScale(2, RoundingMode.HALF_UP).doubleValue();
        this.saveToRepository(repositoryID, name.substring(0, name.indexOf('.')), name.substring(name.indexOf('.')), file.getContentType(), fileSizeMB, resultPath, href);
        return href;
    }
}
