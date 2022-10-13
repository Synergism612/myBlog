package com.synergism.blog.core.article.serviceImpl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.synergism.blog.core.article.entity.Article;
import com.synergism.blog.core.article.mapper.ArticleMapper;
import com.synergism.blog.core.article.service.ArticleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 文章表 服务实现类
 * </p>
 *
 * @author Synergism
 * @since 2022-10-07 10:07:37
 */
@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article> implements ArticleService {

    @Override
    public List<Article> pagination(int currentPage, int pageSize) {
        //从第几条数据开始
        int firstIndex = (currentPage-1) * pageSize;
        //到第几条数据结束
        int lastIndex = currentPage * pageSize;
        //查询得到
        return this.list(new QueryWrapper<Article>().last("limit "+firstIndex+","+lastIndex));
    }
}
