package com.synergism.blog.core.file.serviceImpl;

import com.synergism.blog.core.file.entity.File;
import com.synergism.blog.core.file.mapper.FileMapper;
import com.synergism.blog.core.file.service.FileService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

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

}
