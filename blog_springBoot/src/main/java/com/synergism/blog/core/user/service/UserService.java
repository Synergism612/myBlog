package com.synergism.blog.core.user.service;

import com.synergism.blog.core.user.entity.Author;
import com.synergism.blog.core.user.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;
import com.synergism.blog.core.user.entity.UserInformation;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Synergism
 * @since 2022-08-25 04:30:53
 */
public interface UserService extends IService<User> {

    /**
     * 是否存在
     * @param username 账号
     * @return 存在返回真，不存在返回否
     */
    boolean isExist(String username);

    /**
     * 通过账号获取用户id
     * 未找到用户返回-1
     * @param username 账号
     * @return 用户id
     */
    long getID(String username);

    /**
     * 通过文章id获得文章作者信息
     * @param articleID 文章id
     * @return 作者信息
     */
    Author getAuthorByArticleID(long articleID);

    /**
     * 通过账号获取作者信息
     * @param username 账号
     * @return 作者信息
     */
    Author getAuthorByUsername(String username);

    /**
     * 修改用户头像
     * @param username 账号
     * @param href 链接
     */
    @Transactional
    void updateIcon(String username, String href);
}
