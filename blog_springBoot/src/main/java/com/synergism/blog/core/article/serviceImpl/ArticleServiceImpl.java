package com.synergism.blog.core.article.serviceImpl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.synergism.blog.core.article.entity.Article;
import com.synergism.blog.core.article.entity.Pagination;
import com.synergism.blog.core.article.mapper.ArticleMapper;
import com.synergism.blog.core.article.service.ArticleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.synergism.blog.result.entity.Result;
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
    public Result<Pagination> pagination(int currentPage, int pageSize) {
        //从第几条数据开始
        int firstIndex = (currentPage-1) * pageSize;
        //到第几条数据结束
        int lastIndex = currentPage * pageSize;
        //查询得到
        List<Article> articleList = this.list(new QueryWrapper<Article>().last("limit "+firstIndex+","+lastIndex));
        //总条数
        long total = this.count();
        //封装
        return Result.success(new Pagination(articleList,currentPage,pageSize,total));
    }
}
