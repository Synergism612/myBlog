package com.synergism.blog.api.writeAPI.serviceImpl;

import com.synergism.blog.api.writeAPI.entity.ArticleForm;
import com.synergism.blog.api.writeAPI.entity.ClassifyForm;
import com.synergism.blog.api.writeAPI.entity.TagForm;
import com.synergism.blog.api.writeAPI.service.writeAPIService;
import com.synergism.blog.core.article.entity.Article;
import com.synergism.blog.core.article.entity.ArticleInformation;
import com.synergism.blog.core.article.service.ArticleService;
import com.synergism.blog.core.classify.entity.Classify;
import com.synergism.blog.core.classify.service.ClassifyService;
import com.synergism.blog.core.tag.entity.Tag;
import com.synergism.blog.core.tag.service.TagService;
import com.synergism.blog.core.user.service.UserService;
import com.synergism.blog.result.CodeMsg;
import com.synergism.blog.result.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class WriteAPIServiceImpl implements writeAPIService {

    private final UserService userService;
    private final ArticleService articleService;
    private final ClassifyService classifyService;
    private final TagService tagService;

    @Autowired
    public WriteAPIServiceImpl(UserService userService, ArticleService articleService, ClassifyService classifyService, TagService tagService) {
        this.userService = userService;
        this.articleService = articleService;
        this.classifyService = classifyService;
        this.tagService = tagService;
    }

    @Override
    public Result<List<Classify>> getClassifyList() {
        return Result.success(classifyService.list());
    }

    @Override
    public Result<List<Tag>> getTagList() {
        return Result.success(tagService.list());
    }

    @Override
    public Result<ArticleInformation> getArticle(Long articleID) {
        if (articleService.isExist(articleID)) {
            return Result.success(articleService.getArticleInformationByID(articleID));
        }
        return Result.error(CodeMsg.BIND_ERROR.fillArgs("文章不存在"));

    }

    @Override
    public Result<String> saveArticle(ArticleForm articleForm) {
        long userID = userService.getID(articleForm.getUsername());
        if (userID != -1) {
            articleService.save(userID, articleForm.getIcon(), articleForm.getTitle(), articleForm.getBody(), articleForm.getSynopsis(), articleForm.getIfPrivate(), articleForm.getClassifyID(), articleForm.getTagIDList());
            return Result.success();
        }
        return Result.error(CodeMsg.BIND_ERROR.fillArgs("用户不存在"));
    }

    @Override
    public Result<String> updateArticle(ArticleForm articleForm) {
        Article article = articleService.getById(articleForm.getId());
        if (article != null) {
            article.update(article.getIcon(),
                    article.getTitle(),
                    article.getBody(),
                    article.getSynopsis(),
                    article.getIfPrivate());
            return articleService.updateById(article)
                    ? Result.success()
                    : Result.error(CodeMsg.MESSAGE.fillArgs("更新失败"));
        }
        return Result.error(CodeMsg.BIND_ERROR.fillArgs("文章不存在"));
    }

    @Override
    public Result<String> saveClassify(ClassifyForm classifyForm) {
        long userID = userService.getID(classifyForm.getUsername());
        if (userID != -1) {
            classifyService.save(userID,classifyForm.getName(),classifyForm.getAnnotation());
            return Result.success();
        }
        return Result.error(CodeMsg.BIND_ERROR.fillArgs("用户不存在"));
    }

    @Override
    public Result<String> saveTag(TagForm tagForm) {
        long userID = userService.getID(tagForm.getUsername());
        if (userID != -1) {
            tagService.save(userID,tagForm.getName(),tagForm.getAnnotation());
            return Result.success();
        }
        return Result.error(CodeMsg.BIND_ERROR.fillArgs("用户不存在"));
    }
}
