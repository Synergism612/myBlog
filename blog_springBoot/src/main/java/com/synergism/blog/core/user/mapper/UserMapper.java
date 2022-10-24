package com.synergism.blog.core.user.mapper;

import com.synergism.blog.core.user.entity.AuthorInformation;
import com.synergism.blog.core.user.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.Map;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Synergism
 * @since 2022-08-25 04:30:53
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

    /**
     * 通过文章id查询用户信息
     * @param articleID 文章id
     * @return 用户信息
     */
    User selectOneByArticleID(long articleID);

    AuthorInformation selectAuthorInfoCountByID(long userID);
}
