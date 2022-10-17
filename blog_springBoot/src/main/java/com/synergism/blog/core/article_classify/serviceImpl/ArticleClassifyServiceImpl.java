package com.synergism.blog.core.article_classify.serviceImpl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.synergism.blog.core.article_classify.entity.ArticleClassify;
import com.synergism.blog.core.article_classify.mapper.ArticleClassifyMapper;
import com.synergism.blog.core.article_classify.service.ArticleClassifyService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.synergism.blog.core.classify.entity.Classify;
import com.synergism.blog.core.classify.service.ClassifyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 分类-文章对照表：文章的分类 服务实现类
 * </p>
 *
 * @author Synergism
 * @since 2022-10-11 05:57:44
 */
@Service
public class ArticleClassifyServiceImpl extends ServiceImpl<ArticleClassifyMapper, ArticleClassify> implements ArticleClassifyService {

    private final ClassifyService classifyService;

    @Autowired
    public ArticleClassifyServiceImpl(ClassifyService classifyService) {
        this.classifyService = classifyService;
    }

    @Override
    public List<List<Classify>> getClassifyListByArticleIDList(List<Long> articleIDList) {
        //查询对照表
        List<ArticleClassify> articleClassifyList = this.list(new LambdaQueryWrapper<ArticleClassify>().in(ArticleClassify::getArticleId, articleIDList));

        //查询分类表
        List<Classify> classifyList = classifyService.listByIds(articleClassifyList.stream()
                .map(ArticleClassify::getClassifyId)
                .distinct()
                .collect(Collectors.toList()));

        //获取对应分类
        return articleIDList.stream()
                .map(articleID -> {
                    List<Long> classifyIDList = articleClassifyList.stream()
                            .filter(articleClassify -> articleClassify
                                            .getArticleId()
                                            .equals(articleID))
                            .map(ArticleClassify::getClassifyId).collect(Collectors.toList());

                    return classifyIDList.stream().map(classifyID -> {
                        for (Classify classify : classifyList) {
                            if (classify.getId().equals(classifyID))
                                return classify;
                        }
                        return null;
                    }).collect(Collectors.toList());
                })
                .collect(Collectors.toList());
    }
}
