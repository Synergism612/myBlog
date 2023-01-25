package com.synergism.blog.utils;

import com.synergism.blog.exception.custom.IOErrorException;
import org.springframework.web.multipart.MultipartFile;

/**
 * 文件工具类
 */
public class FileUtil {
    public static void ifImg(MultipartFile file){
        String contentType = file.getContentType();
        if (contentType == null || contentType.isEmpty()) {
            throw new IOErrorException("类型不可知");
        }
        if (!contentType.equals("image/jpeg") && !contentType.equals("image/png")) {
            throw new IOErrorException("只能为image/jpeg,image/png类型");
        }
    }
}
