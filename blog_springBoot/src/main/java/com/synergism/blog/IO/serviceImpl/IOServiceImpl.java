package com.synergism.blog.IO.serviceImpl;

import com.synergism.blog.IO.service.IOService;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

@Service
public class IOServiceImpl implements IOService {

    private final String base = System.getProperty("user.dir") + "/";

    @Override
    public boolean write(String parentPath, MultipartFile file) {
        parentPath = base + parentPath;
        File dest = new File(parentPath + '/' + file.getOriginalFilename());
        if (!dest.getParentFile().exists()) {
            if (!dest.getParentFile().mkdirs()) {
                return false;
            }
        }
        try {
            file.transferTo(dest);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    @Override
    public boolean delete(String path) {
        path = base + path;
        return FileSystemUtils.deleteRecursively(new File(path));
    }

    @Override
    public boolean mkdir(String parentPath, String name) {
        parentPath = base + parentPath + "/" + name;
        File dest = new File(parentPath);
        if (dest.exists()) return true;
        return dest.mkdir();
    }
}
