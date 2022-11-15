package com.synergism.blog.core.article.serviceImpl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.synergism.blog.core.article.entity.*;
import com.synergism.blog.core.article.enumeration.ArticleSort;
import com.synergism.blog.core.article.mapper.ArticleMapper;
import com.synergism.blog.core.article.service.ArticleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.synergism.blog.utils.TypeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    public List<ArticleInformation> getArticleInformationListByUsername(String username) {
        List<ArticleInformation> result = mapper.selectArticleInformationListByUsername(username);
        return result.size() == 0 ? null : result;
    }

    @Override
    public List<ArticleInformation> getPublicArticleInformationList() {
        List<ArticleInformation> result = mapper.selectPublicArticleInformationList();
        return result.size() == 0 ? null : result;
    }

    @Override
    public List<ArticleInformation> sortArticleInformationList(List<ArticleInformation> articleInformationList, ArticleSort articleSort) {
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
    public List<Article> getSameClassifyArticleList(long id) {
        List<Article> result = mapper.selectSameClassifyArticleList(id);
        return result.size() == 0 ? null : result;

    }

    @Override
    public List<ArticleTagNominate> getMoreTagArticleList(long id) {
        List<ArticleTagNominate> result = mapper.selectMoreTagArticleList(id);
        return result.size() == 0 ? null : result;
    }

    @Override
    public boolean isExist(String username,long articleID) {
        return mapper.selectOneByUsernameAndArticleID(username,articleID) != null;
    }

    @Override
    public boolean isExist(List<Long> articleIDList) {
        List<Article> articleList = mapper.selectList(new LambdaQueryWrapper<Article>().in(Article::getId, articleIDList));
        return articleList.size() != 0;
    }

    @Override
    public List<ArticleInformation> getArticleInformationListByKeyword(List<ArticleInformation> articleInformationList, String keyword) {
        if (articleInformationList.size() == 0) return null;
        //筛选方法，在文章的作者昵称、标题、摘要中存在关键字
        return articleInformationList.stream().filter(articleInformation ->
                articleInformation.getNickname().contains(keyword) ||
                        articleInformation.getTitle().contains(keyword) ||
                        articleInformation.getSynopsis().contains(keyword)
        ).collect(Collectors.toList());
    }

    @Override
    public List<ArticleInformation> getArticleInformationListByClassifyList(List<ArticleInformation> articleInformationList, List<Long> classifyIDList) {
        if (articleInformationList.size() == 0) return null;
        return articleInformationList.stream().filter(articleInformation ->
                classifyIDList.contains(articleInformation.getClassify().getId())
        ).collect(Collectors.toList());
    }

    @Override
    public List<ArticleInformation> getArticleInformationListByTagList(List<ArticleInformation> articleInformationList, List<Long> tagIDList) {
        if (articleInformationList.size() == 0) return null;
        return articleInformationList.stream().filter(articleInformation ->
                articleInformation.getTagList().stream().anyMatch(tag ->
                        tagIDList.contains(tag.getId()))
        ).collect(Collectors.toList());
    }

    @Override
    public Pagination Pagination(List<ArticleInformation> articleInformationList, int currentPage, int pageSize) {
        if (TypeUtil.isNull(articleInformationList)) {
            return new Pagination(null, 0);
        }
        int startIndex = (currentPage - 1) * pageSize;
        int endIndex = currentPage * pageSize;
        int total = articleInformationList.size();
        endIndex = Math.min(endIndex, total);
        return new Pagination(articleInformationList.subList(startIndex, endIndex), total);
    }

    @Override
    public Pagination getPagination(int currentPage, int pageSize, ArticleSort articleSort, String username, String keyword, List<Long> classifyIDList, List<Long> tagIDList) {
        List<ArticleInformation> result;
        //传入账号是否为空
        if (username.isEmpty()) {
            //为空获取公开文章列表
            result = this.getPublicArticleInformationList();
        } else {
            //不为空获取用户下的文章列表
            result = this.getArticleInformationListByUsername(username);
        }
        //传入关键字不为空且暂存结果不为空
        if (!keyword.isEmpty() && result != null) {
            //根据关键字获取文章列表
            result = this.getArticleInformationListByKeyword(result, keyword);
        }
        //传入分类id列表不为空且暂存结果不为空
        if (classifyIDList != null && result != null) {
            //根据分类id列表获取文章列表
            result = this.getArticleInformationListByClassifyList(result, classifyIDList);
        }
        //传入标签id列表不为空且暂存结果不为空
        if (tagIDList != null && result != null) {
            //根据标签id列表获取文章列表
            result = this.getArticleInformationListByTagList(result, tagIDList);
        }
        //暂存结果不为空
        if (result != null) {
            //排序
            result = this.sortArticleInformationList(result, articleSort);
        }
        //封装分页
        return this.Pagination(result, currentPage, pageSize);
    }

    @Override
    @Transactional
    public void save(long userID, String icon, String title, String body, String synopsis, int ifPrivate, Long classifyID, List<Long> tagIDList) {
        Article article = new Article(null, icon, title, body, synopsis, 0L, 0L, ifPrivate);
        mapper.insert(article);
        mapper.bundle(article.getId(), userID, classifyID, tagIDList);
    }

    @Override
    @Transactional
    public void remove(List<Long> articleIDList, long userID) {
        mapper.unbundled(articleIDList, userID);
        mapper.deleteBatchIds(articleIDList);
    }

    @Override
    public ArticleInformation getArticleInformationByID(long articleID) {
        return mapper.selectArticleInformationByArticleID(articleID);
    }

    @Override
    public List<Archive> getPublicArchive() {
        List<Archive> result = mapper.selectPublicArchive();
        return result.size() == 0 ? null : result;
    }

    @Override
    public List<Archive> getArchiveByUserID(long userID) {
        List<Archive> result = mapper.selectArchiveByUserID(userID);
        return result.size() == 0 ? null : result;
    }

}
