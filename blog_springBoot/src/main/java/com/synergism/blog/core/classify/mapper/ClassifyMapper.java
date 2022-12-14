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
     * 查询分类信息
     * @return 分类信息列表
     */
    List<ClassifyInformation> selectAllClassifyInformationList(int limit);

    /**
     * 查询该文章对应的分类
     * @param articleID 文章id
     * @return 分类
     */
    Classify selectOneByArticleID(long articleID);

    /**
     * 查询该用户使用过的分类
     * @param username 账号
     * @return 分类列表
     */
    List<Classify> selectUsedListByUsername(String username);

    /**
     * 绑定新的分类
     * @param classifyID 分类id
     * @param userID 用户id
     */
    void bundle(long classifyID, long userID);
}
