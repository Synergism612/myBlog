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
     * 获得全部分类信息列表
     * @return 分类信息列表
     */
    List<ClassifyInformation> getAllClassifyInformationList();

    /**
     * 通过用户名筛选分类列表
     * @param username 用户名
     * @return 分类列表
     */
    List<ClassifyInformation> getClassifyInformationListByUsername(String username);

    /**
     * 通过文章id找到对应分类
     * @param articleID 文章id
     * @return 分类
     */
    Classify getOneByArticleID(long articleID);
}
