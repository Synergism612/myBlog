package com.synergism.blog.IO.serviceImpl;

import com.synergism.blog.IO.service.IOService;
import com.synergism.blog.exception.custom.IOErrorException;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

@Service
public class IOServiceImpl implements IOService {

    //分隔符
    private final String separator = File.separator;

    private final String base = System.getProperty("user.dir") + separator + "blog_resources" + separator;

    @Override
    public String write(String parentPath, MultipartFile file) {
        parentPath = parentPath + separator + file.getOriginalFilename();
        String path = base + parentPath;
        File dest = new File(path);
        if (dest.exists()) {
            throw new IOErrorException("文件已存在");
        }
        if (!dest.getParentFile().exists()) {
            if (!dest.getParentFile().mkdirs()) {
                throw new IOErrorException("目录创建失败");
            }
        }
        try {
            file.transferTo(dest);
        } catch (Exception e) {
            throw new IOErrorException(e.getMessage());
        }
        return parentPath;
    }

    @Override
    public boolean delete(String path) {
        path = base + path;
        return FileSystemUtils.deleteRecursively(new File(path));
    }

    @Override
    public boolean mkdir(String parentPath, String name) {
        String path = base + parentPath + separator + name;
        File dest = new File(path);
        if (dest.exists()) return true;
        return dest.mkdirs();
    }

    @Override
    public boolean mkdir(String path) {
        path = base + path;
        File dest = new File(path);
        if (dest.exists()) return true;
        return dest.mkdirs();
    }

    @Override
    public boolean repository(String username) {
        String path = base + username;
        File dest = new File(path);
        if (dest.exists()) return true;
        return dest.mkdirs();
    }
}
