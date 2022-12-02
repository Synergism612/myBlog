package com.synergism.blog.api.indexAPI.serviceImpl;

import com.synergism.blog.api.indexAPI.service.IndexAPIService;
import com.synergism.blog.core.article.entity.Pagination;
import com.synergism.blog.core.article.enumeration.ArticleSort;
import com.synergism.blog.core.article.service.ArticleService;
import com.synergism.blog.core.classify.entity.ClassifyInformation;
import com.synergism.blog.core.classify.service.ClassifyService;
import com.synergism.blog.core.tag.entity.TagInformation;
import com.synergism.blog.core.tag.service.TagService;
import com.synergism.blog.core.user.entity.Author;
import com.synergism.blog.core.user.entity.UserInformation;
import com.synergism.blog.core.user.service.UserService;
import com.synergism.blog.result.CodeMsg;
import com.synergism.blog.result.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IndexAPIServiceImpl implements IndexAPIService {

    private final UserService userService;
    private final ArticleService articleService;
    private final TagService tagService;
    private final ClassifyService classifyService;

    @Autowired
    public IndexAPIServiceImpl(UserService userService, ArticleService articleService, TagService tagService, ClassifyService classifyService) {
        this.userService = userService;
        this.articleService = articleService;
        this.tagService = tagService;
        this.classifyService = classifyService;
    }

    @Override
    public Result<Author> getAuthor(String username) {
        return Result.success(userService.getAuthorByUsername(username));
    }

    @Override
    public Result<List<TagInformation>> getTag() {
        List<TagInformation> result;
        result = tagService.getTagInformationList(20);
        return Result.success(result);
    }

    @Override
    public Result<List<ClassifyInformation>> getClassify() {
        List<ClassifyInformation> result;
        result = classifyService.getClassifyInformationList(20);
        return Result.success(result);
    }

    @Override
    public Result<Pagination> getArticle(int currentPage, int pageSize, ArticleSort articleSort, String username) {
        return Result.success(articleService.getPagination(currentPage, pageSize, articleSort, username, "", null, null));
    }

    @Override
    public Result<String> removeArticle(String username, List<Long> articleIDList) {
        long userID = userService.getID(username);
        if (!articleService.isExist(username,articleIDList)) {
            return Result.error(CodeMsg.BIND_ERROR.fillArgs("文章不存在"));
        }
        if (userID == -1) {
            return Result.error(CodeMsg.BIND_ERROR.fillArgs("用户不存在"));
        }
        articleService.remove(articleIDList, userID);
        return Result.success();
    }
}
