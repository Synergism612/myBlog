package com.synergism.blog.core.article.serviceImpl;

import com.synergism.blog.core.article.entity.Article;
import com.synergism.blog.core.article.entity.ArticleInformation;
import com.synergism.blog.core.article.enumeration.ArticleSort;
import com.synergism.blog.core.article.mapper.ArticleMapper;
import com.synergism.blog.core.article.service.ArticleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;


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

    private final ArticleMapper mapper;

    @Autowired
    public ArticleServiceImpl(ArticleMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public List<ArticleInformation> ArticleInformationListSort(List<ArticleInformation> articleInformationList, ArticleSort articleSort) {
        switch (articleSort) {
            case views:
                return articleInformationList.stream().sorted(Comparator.comparing(ArticleInformation::getViews).reversed()).collect(Collectors.toList());
            case like_count:
                return articleInformationList.stream().sorted(Comparator.comparing(ArticleInformation::getLikeCount).reversed()).collect(Collectors.toList());
            case modify_time:
                return articleInformationList.stream().sorted(Comparator.comparing(ArticleInformation::getModifyTime).reversed()).collect(Collectors.toList());
            default:
                return articleInformationList.stream().sorted(Comparator.comparing(ArticleInformation::getCreationTime).reversed()).collect(Collectors.toList());
        }
    }

    @Override
    public List<ArticleInformation> getArticleInformationList() {
        return mapper.getArticleInformationList();
    }

    @Override
    public List<ArticleInformation> getArticleInformationListByUsername(String username) {
        return this.getArticleInformationList().stream().filter(articleInformation -> articleInformation.getUsername().equals(username)).collect(Collectors.toList());
    }
}
