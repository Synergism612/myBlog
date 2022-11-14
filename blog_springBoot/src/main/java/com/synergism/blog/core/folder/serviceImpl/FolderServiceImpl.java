package com.synergism.blog.core.folder.serviceImpl;

import com.synergism.blog.core.folder.entity.Folder;
import com.synergism.blog.core.folder.mapper.FolderMapper;
import com.synergism.blog.core.folder.service.FolderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 文件夹表 服务实现类
 * </p>
 *
 * @author Synergism
 * @since 2022-11-14 09:38:43
 */
@Service
public class FolderServiceImpl extends ServiceImpl<FolderMapper, Folder> implements FolderService {

}
