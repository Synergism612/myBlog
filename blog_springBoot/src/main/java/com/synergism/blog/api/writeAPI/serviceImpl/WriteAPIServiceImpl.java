package com.synergism.blog.api.writeAPI.serviceImpl;

import com.synergism.blog.api.writeAPI.entity.ArticleForm;
import com.synergism.blog.api.writeAPI.service.writeAPIService;
import com.synergism.blog.core.article.service.ArticleService;
import com.synergism.blog.core.classify.entity.ClassifyInformation;
import com.synergism.blog.core.classify.service.ClassifyService;
import com.synergism.blog.core.tag.entity.TagInformation;
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
    public Result<List<ClassifyInformation>> getClassifyList(String username) {
        return Result.success(classifyService.getClassifyInformationListByUsername(username));
    }

    @Override
    public Result<List<TagInformation>> getTagList(String username) {
        return Result.success(tagService.getTagInformationListByUsername(username));
    }

    @Override
    public Result<String> saveArticle(ArticleForm articleForm) {
        long userID = userService.getID(articleForm.getUsername());
        if (userID != -1) {
            return articleService.save(userID, articleForm.getIcon(), articleForm.getTitle(), articleForm.getBody(), articleForm.getSynopsis(), articleForm.getIfPrivate(), articleForm.getClassifyID(), articleForm.getTagIDList())
                    ? Result.success()
                    : Result.error(CodeMsg.MESSAGE.fillArgs("添加失败"));
        }
        return Result.error(CodeMsg.BIND_ERROR.fillArgs("用户不存在"));
    }
}
