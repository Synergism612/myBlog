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
     * 获取标签信息列表
     * @return 标签信息列表
     */
    List<TagInformation> getTagInformationList(int limit);

    /**
     * 通过文章id获取标签列表
     * @param articleID 文章id
     * @return 标签列比奥
     */
    List<Tag> getListByArticleID(long articleID);

    /**
     * 获取该用户使用过的标签
     * @param username 账号
     * @return 标签列表
     */
    List<Tag> getUsedListByUsername(String username);

    /**
     * 保存新的标签
     * @param userID 用户id
     * @param name 名称
     * @param annotation 注释
     */
    void save(long userID, String name, String annotation);
}
