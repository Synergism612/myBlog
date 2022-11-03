package com.synergism.blog.core.article.serviceImpl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.synergism.blog.core.article.entity.Article;
import com.synergism.blog.core.article.entity.ArticleInformation;
import com.synergism.blog.core.article.entity.ArticleTagNominate;
import com.synergism.blog.core.article.entity.Pagination;
import com.synergism.blog.core.article.enumeration.ArticleSort;
import com.synergism.blog.core.article.mapper.ArticleMapper;
import com.synergism.blog.core.article.service.ArticleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.synergism.blog.utils.TypeUtil;
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
    public Pagination Pagination(List<ArticleInformation> articleInformationList, int currentPage, int pageSize) {
        if(TypeUtil.isNull(articleInformationList)){
            return new Pagination(null, 0);
        }
        int startIndex = (currentPage - 1) * pageSize;
        int endIndex = currentPage * pageSize;
        int total = articleInformationList.size();
        endIndex = Math.min(endIndex, total);
        return new Pagination(articleInformationList.subList(startIndex, endIndex), total);
    }

    @Override
    public List<ArticleInformation> getAllArticleInformationList() {
        return mapper.selectAllArticleInformationList();
    }

    @Override
    public List<ArticleInformation> ArticleInformationListSort(List<ArticleInformation> articleInformationList, ArticleSort articleSort) {
        if (articleInformationList.size() == 0) return null;
        switch (articleSort) {
            case views:
                return articleInformationList.stream().sorted(Comparator.comparing(ArticleInformation::getViews).reversed()).collect(Collectors.toList());
            case like_count:
                return articleInformationList.stream().sorted(Comparator.comparing(ArticleInformation::getLikeCount).reversed()).collect(Collectors.toList());
            case comment_count:
                return articleInformationList.stream().sorted(Comparator.comparing(ArticleInformation::getCommentCount).reversed()).collect(Collectors.toList());
            case modify_time:
                return articleInformationList.stream().sorted(Comparator.comparing(ArticleInformation::getModifyTime).reversed()).collect(Collectors.toList());
            default:
                return articleInformationList.stream().sorted(Comparator.comparing(ArticleInformation::getCreationTime).reversed()).collect(Collectors.toList());
        }
    }

    @Override
    public List<ArticleInformation> getArticleInformationListByUsername(String username) {
        List<ArticleInformation> result = this.getAllArticleInformationList()
                .stream()
                .filter(articleInformation ->
                        articleInformation.getUsername().equals(username))
                .collect(Collectors.toList());
        return result.size() == 0 ? null : result;
    }

    @Override
    public List<ArticleInformation> getArticleInformationListByPublic() {
        List<ArticleInformation> result = this.getAllArticleInformationList()
                .stream()
                .filter(articleInformation ->
                        articleInformation.getIfPrivate() == 0)
                .collect(Collectors.toList());
        return result.size() == 0 ? null : result;
    }

    @Override
    public List<Article> getOneClassifyArticleList(long id) {
        List<Article> result = mapper.selectOneClassifyArticleList(id)
                .stream()
                .sorted(Comparator.comparing(Article::getCreationTime)
                        .reversed())
                .collect(Collectors.toList());
        return result.size() == 0 ? null : result;

    }

    @Override
    public List<ArticleTagNominate> getMoreTagArticleList(long id) {
        List<ArticleTagNominate> result = mapper.selectMoreTagArticleList(id)
                .stream()
                .sorted(Comparator.comparing(ArticleTagNominate::getTagCount)
                        .reversed())
                .collect(Collectors.toList());
        return result.size() == 0 ? null : result;

    }

    @Override
    public boolean isExist(Long articleID) {
        return this.getOne(new LambdaQueryWrapper<Article>().eq(Article::getId, articleID)) != null;
    }

    @Override
    public List<ArticleInformation> searchArticleInformationListByKeyword(List<ArticleInformation> articleInformationList, String keyword) {
        if (articleInformationList.size() == 0) return null;
        return articleInformationList.stream().filter(articleInformation ->
                        articleInformation.getNickname().contains(keyword) ||
                        articleInformation.getTitle().contains(keyword) ||
                        articleInformation.getSynopsis().contains(keyword)
        ).collect(Collectors.toList());
    }

    @Override
    public List<ArticleInformation> searchArticleInformationListByClassifyList(List<ArticleInformation> articleInformationList, List<Long> classifyIDList) {
        if (articleInformationList.size() == 0) return null;
        return articleInformationList.stream().filter(articleInformation ->
                classifyIDList.contains(articleInformation.getClassify().getId())
        ).collect(Collectors.toList());
    }

    @Override
    public List<ArticleInformation> searchArticleInformationListByTagList(List<ArticleInformation> articleInformationList, List<Long> tagIDList) {
        if (articleInformationList.size() == 0) return null;
        return articleInformationList.stream().filter(articleInformation ->
                articleInformation.getTagList().stream().anyMatch(tag ->
                        tagIDList.contains(tag.getId()))
        ).collect(Collectors.toList());
    }
}
