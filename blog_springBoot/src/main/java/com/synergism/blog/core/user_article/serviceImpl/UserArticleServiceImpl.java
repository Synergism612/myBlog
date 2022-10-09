package com.synergism.blog.core.user_article.serviceImpl;

import com.synergism.blog.core.user_article.entity.UserArticle;
import com.synergism.blog.core.user_article.mapper.UserArticleMapper;
import com.synergism.blog.core.user_article.service.UserArticleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户-文章对照表：用户的文章 服务实现类
 * </p>
 *
 * @author Synergism
 * @since 2022-10-09 04:43:45
 */
@Service
public class UserArticleServiceImpl extends ServiceImpl<UserArticleMapper, UserArticle> implements UserArticleService {

}
