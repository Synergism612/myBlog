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
     * 获取全部标签信息列表
     * @return 标签信息列表
     */
    List<TagInformation> selectAllTagInformationList();

    /**
     * 通过文章id获取标签列表
     * @param articleID 文章id
     * @return 标签列比奥
     */
    List<Tag> selectListByArticleID(long articleID);
}
