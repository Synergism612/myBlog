package com.synergism.blog.core.tag.mapper;

import com.synergism.blog.core.tag.entity.Tag;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.synergism.blog.core.tag.entity.TagInformation;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <p>
 * 标签表 Mapper 接口
 * </p>
 *
 * @author Synergism
 * @since 2022-10-11 05:58:34
 */
@Mapper
public interface TagMapper extends BaseMapper<Tag> {

    /**
     * 查询全部标签信息列表
     * @return 标签信息列表
     */
    List<TagInformation> selectAllTagInformationList();

    /**
     * 查询该用户下标签信息
     * @param username 账号
     * @return 标签信息列表
     */
    List<TagInformation> selectTagInformationListByUsername(String username);

    /**
     * 查询该文章下标签信息
     * @param articleID 文章id
     * @return 标签信息列表
     */
    List<Tag> selectListByArticleID(long articleID);

    /**
     * 查询该用户使用过的标签
     * @param username 账号
     * @return 标签列表
     */
    List<Tag> selectUsedListByUsername(String username);

    /**
     * 绑定新的标签
     * @param tagID 标签id
     * @param userID 用户id
     */
    void bundle(long tagID, long userID);
}
