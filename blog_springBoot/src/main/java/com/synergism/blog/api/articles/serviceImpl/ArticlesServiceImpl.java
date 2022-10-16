package com.synergism.blog.api.articles.serviceImpl;

import com.synergism.blog.core.article.entity.ArticleInformation;
import com.synergism.blog.api.articles.entity.Pagination;
import com.synergism.blog.api.articles.enumeration.OrderBy;
import com.synergism.blog.api.articles.service.ArticlesService;
import com.synergism.blog.core.article.service.ArticleService;
import com.synergism.blog.result.entity.Result;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ArticlesServiceImpl implements ArticlesService {

    private final ArticleService articleService;

    public ArticlesServiceImpl(ArticleService articleService) {
        this.articleService = articleService;
    }

    @Override
    public Result<Pagination> getPagination(int currentPage, int pageSize, OrderBy orderBy) {
        List<ArticleInformation> articleInformationList = articleService.getAllArticleInformation();
        //获取公开文章
        articleInformationList = articleInformationList.stream().filter(articleInformation -> articleInformation.getIfPrivate() == 0).collect(Collectors.toList());
        //获取排序结果
        List<ArticleInformation> result;
        switch (orderBy) {
            case creationTime: {
                result = articleInformationList.stream().sorted(Comparator.comparing(ArticleInformation::getCreationTime).reversed()).collect(Collectors.toList());
                break;
            }
            case modifyTime: {
                result = articleInformationList.stream().sorted(Comparator.comparing(ArticleInformation::getModifyTime).reversed()).collect(Collectors.toList());
                break;
            }
            case commentCount: {
                result = articleInformationList.stream().sorted(Comparator.comparing(ArticleInformation::getCommentCount).reversed()).collect(Collectors.toList());
                break;
            }
            case views: {
                result = articleInformationList.stream().sorted(Comparator.comparing(ArticleInformation::getViews).reversed()).collect(Collectors.toList());
                break;
            }
            case like_count: {
                result = articleInformationList.stream().sorted(Comparator.comparing(ArticleInformation::getLikeCount).reversed()).collect(Collectors.toList());
                break;
            }
            default:
                throw new IllegalArgumentException("排序错误");
        }
        //分页
        int startIndex = (currentPage - 1) * pageSize;
        int endIndex = currentPage * pageSize;
        int total = result.size();
        if (endIndex>total) endIndex = total;
        result = result.subList(startIndex, endIndex);
        //封装结果
        return Result.success(new Pagination(result, currentPage, pageSize, total));
    }
}
