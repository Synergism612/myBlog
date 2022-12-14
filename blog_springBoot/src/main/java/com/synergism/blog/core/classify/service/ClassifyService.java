package com.synergism.blog.core.classify.service;

import com.synergism.blog.core.classify.entity.Classify;
import com.baomidou.mybatisplus.extension.service.IService;
import com.synergism.blog.core.classify.entity.ClassifyInformation;

import java.util.List;

/**
 * <p>
 * 分类表 服务类
 * </p>
 *
 * @author Synergism
 * @since 2022-10-11 05:58:03
 */
public interface ClassifyService extends IService<Classify> {
    /**
     * 获得分类云
     * @return 分类信息列表
     */
    List<ClassifyInformation> getClassifyInformationList(int limit);

    /**
     * 通过文章id找到对应分类
     * @param articleID 文章id
     * @return 分类
     */
    Classify getOneByArticleID(long articleID);

    /**
     * 获取该用户使用过的分类
     * @param username 账号
     * @return 分类列表
     */
    List<Classify> getUsedListByUsername(String username);

    /**
     * 保存新的分类
     * @param userID 用户id
     * @param name 名称
     * @param annotation 注释
     */
    void save(long userID,String name,String annotation);

}
