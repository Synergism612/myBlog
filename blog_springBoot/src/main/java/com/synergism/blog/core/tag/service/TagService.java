package com.synergism.blog.core.tag.service;

import com.synergism.blog.core.tag.entity.Tag;
import com.baomidou.mybatisplus.extension.service.IService;
import com.synergism.blog.core.tag.entity.TagInformation;

import java.util.List;

/**
 * <p>
 * 标签表 服务类
 * </p>
 *
 * @author Synergism
 * @since 2022-10-11 05:58:34
 */
public interface TagService extends IService<Tag> {

    /**
     * 获取全部标签信息列表
     * @return 标签信息列表
     */
    List<TagInformation> getAllTagInformationList();

    /**
     * 通过用户名筛选标签列表
     * @param username 用户名
     * @return 标签列表
     */
    List<TagInformation> getTagInformationListByUsername(String username);

    /**
     * 通过文章id获取标签列表
     * @param articleID 文章id
     * @return 标签列比奥
     */
    List<Tag> getListByArticleID(long articleID);

}
