package com.synergism.blog.core.user.mapper;

import com.synergism.blog.core.user.entity.Author;
import com.synergism.blog.core.user.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

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
     * 查询该文章的作者
     * @param articleID 文章id
     * @return 作者信息
     */
    Author selectAuthorByArticleID(long articleID);

    /**
     * 查询该用户的作者信息
     * @param username 账号
     * @return 作者信息
     */
    Author selectAuthorByUsername(String username);

    /**
     * 更新用户头像
     * @param username 账号
     * @param href 链接
     */
    void updateIcon(String username, String href);
}
