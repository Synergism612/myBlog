package com.synergism.blog.core.classify.mapper;

import com.synergism.blog.core.classify.entity.Classify;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.synergism.blog.core.classify.entity.ClassifyInformation;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <p>
 * 分类表 Mapper 接口
 * </p>
 *
 * @author Synergism
 * @since 2022-10-11 05:58:03
 */
@Mapper
public interface ClassifyMapper extends BaseMapper<Classify> {

    /**
     * 获得全部分类信息列表
     * 
     * @return 分类信息列表
     */
    List<ClassifyInformation> getAllClassifyInformationList();

    /**
     * 通过文章id找到对应分类
     * @param articleID 文章id
     * @return 分类
     */
    Classify getOneByArticleID(long articleID);
}
