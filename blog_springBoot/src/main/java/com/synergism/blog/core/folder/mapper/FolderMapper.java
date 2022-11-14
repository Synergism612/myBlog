package com.synergism.blog.core.folder.mapper;

import com.synergism.blog.core.folder.entity.Folder;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 文件夹表 Mapper 接口
 * </p>
 *
 * @author Synergism
 * @since 2022-11-14 09:38:43
 */
@Mapper
public interface FolderMapper extends BaseMapper<Folder> {

}
