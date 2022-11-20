package com.synergism.blog.api.writeAPI.serviceImpl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.synergism.blog.IO.service.IOService;
import com.synergism.blog.api.writeAPI.entity.ArticleForm;
import com.synergism.blog.api.writeAPI.entity.ClassifyForm;
import com.synergism.blog.api.writeAPI.entity.TagForm;
import com.synergism.blog.api.writeAPI.service.writeAPIService;
import com.synergism.blog.core.article.entity.Article;
import com.synergism.blog.core.article.entity.ArticleInformation;
import com.synergism.blog.core.article.service.ArticleService;
import com.synergism.blog.core.classify.entity.Classify;
import com.synergism.blog.core.classify.service.ClassifyService;
import com.synergism.blog.core.repository.entity.Repository;
import com.synergism.blog.core.repository.service.FileService;
import com.synergism.blog.core.repository.service.FolderService;
import com.synergism.blog.core.repository.service.RepositoryService;
import com.synergism.blog.core.tag.entity.Tag;
import com.synergism.blog.core.tag.service.TagService;
import com.synergism.blog.core.user.service.UserService;
import com.synergism.blog.result.CodeMsg;
import com.synergism.blog.result.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;


@Service
public class WriteAPIServiceImpl implements writeAPIService {

    //分隔符
    private final String separator = File.separator;

    private final UserService userService;
    private final ArticleService articleService;
    private final ClassifyService classifyService;
    private final TagService tagService;
    private final RepositoryService repositoryService;
    private final FolderService folderService;
    private final FileService fileService;
    private final IOService ioService;

    @Autowired
    public WriteAPIServiceImpl(UserService userService, ArticleService articleService, ClassifyService classifyService, TagService tagService, RepositoryService repositoryService, FolderService folderService, FileService fileService, IOService ioService) {
        this.userService = userService;
        this.articleService = articleService;
        this.classifyService = classifyService;
        this.tagService = tagService;
        this.repositoryService = repositoryService;
        this.folderService = folderService;
        this.fileService = fileService;
        this.ioService = ioService;
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
    public Result<ArticleInformation> getArticle(String username, long articleID) {
        if (!articleService.isExist(username, articleID)) {
            return Result.error(CodeMsg.BIND_ERROR.fillArgs("文章不存在"));
        }
        return Result.success(articleService.getArticleInformationByID(articleID));

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
        if (!articleService.isExist(articleForm.getUsername(), articleForm.getId())) {
            return Result.error(CodeMsg.BIND_ERROR.fillArgs("文章不存在"));
        }
        Article article = articleService.getById(articleForm.getId());
        if (article == null) {
            return Result.error(CodeMsg.BIND_ERROR.fillArgs("文章不存在"));
        }
        article.update(article.getIcon(),
                article.getTitle(),
                article.getBody(),
                article.getSynopsis(),
                article.getIfPrivate());
        return articleService.updateById(article)
                ? Result.success()
                : Result.error(CodeMsg.MESSAGE.fillArgs("更新失败"));
    }

    @Override
    public Result<String> saveClassify(ClassifyForm classifyForm) {
        long userID = userService.getID(classifyForm.getUsername());
        if (userID != -1) {
            classifyService.save(userID, classifyForm.getName(), classifyForm.getAnnotation());
            return Result.success();
        }
        return Result.error(CodeMsg.BIND_ERROR.fillArgs("用户不存在"));
    }

    @Override
    public Result<String> saveTag(TagForm tagForm) {
        long userID = userService.getID(tagForm.getUsername());
        if (userID != -1) {
            tagService.save(userID, tagForm.getName(), tagForm.getAnnotation());
            return Result.success();
        }
        return Result.error(CodeMsg.BIND_ERROR.fillArgs("用户不存在"));
    }

    @Override
    public Result<String> saveArticleIcon(String username, MultipartFile file) {
        String path = username + separator + "blog" + separator + "icon" + separator;
        Repository repository = repositoryService.getOne(new LambdaQueryWrapper<Repository>().eq(Repository::getPath, username + separator));
        if (repository == null) {
            return Result.error(CodeMsg.BIND_ERROR.fillArgs("仓库不存在"));
        }
        long folderID = folderService.update(repository.getId(), path);
        String resultPath = ioService.write(path, file);
        return Result.success(fileService.saveToFolder(folderID,file,resultPath));
    }
}
