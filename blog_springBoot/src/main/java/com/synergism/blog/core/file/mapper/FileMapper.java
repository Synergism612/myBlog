package com.synergism.blog.core.file.mapper;

import com.synergism.blog.core.file.entity.File;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 文件表 Mapper 接口
 * </p>
 *
 * @author Synergism
 * @since 2022-11-14 09:39:02
 */
@Mapper
public interface FileMapper extends BaseMapper<File> {

}
