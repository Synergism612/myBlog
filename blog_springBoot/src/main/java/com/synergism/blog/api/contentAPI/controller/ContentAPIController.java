package com.synergism.blog.api.contentAPI.controller;


import com.synergism.blog.api.contentAPI.entity.CommentForm;
import com.synergism.blog.api.contentAPI.service.ContentAPIService;
import com.synergism.blog.core.article.entity.Article;
import com.synergism.blog.core.article.entity.ArticleTagNominate;
import com.synergism.blog.core.classify.entity.Classify;
import com.synergism.blog.core.comment.entity.CommentParent;
import com.synergism.blog.core.tag.entity.Tag;
import com.synergism.blog.core.user.entity.Author;
import com.synergism.blog.result.CodeMsg;
import com.synergism.blog.result.Result;
import com.synergism.blog.utils.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 内容页接口
 */
@RestController
@RequestMapping("/api/blog/content")
public class ContentAPIController {

    private final ContentAPIService service;

    @Autowired
    public ContentAPIController(ContentAPIService service) {
        this.service = service;
    }

    /**
     * 内容页文章信息获取接口
     * @param articleID 文章id
     * @return 文章信息
     */
    @GetMapping("article")
    public Result<Article> article(@RequestParam long articleID){
        return service.getArticle(articleID);
    }
    /**
     * 内容页作者信息获取接口
     * @param articleID 文章id
     * @return 作者信息
     */
    @GetMapping("author")
    public Result<Author> author(@RequestParam long articleID){
        return service.getAuthor(articleID);
    }
    /**
     * 内容页分类信息获取接口
     * @param articleID 文章id
     * @return 分类信息
     */
    @GetMapping("classify")
    public Result<Classify> classify(@RequestParam long articleID){
        return service.getClassify(articleID);
    }
    /**
     * 内容页标签信息获取接口
     * @param articleID 文章id
     * @return 标签信息
     */
    @GetMapping("tagList")
    public Result<List<Tag>> tagList(@RequestParam long articleID){
        return service.getTagList(articleID);
    }
    /**
     * 内容页评论获取接口
     * @param articleID 文章id
     * @return 评论信息列表
     */
    @GetMapping("commentList")
    public Result<List<CommentParent>> commentList(@RequestParam long articleID){
        return service.getCommentList(articleID);
    }
    /**
     * 内容页同分类推荐获取接口
     * @param articleID 文章id
     * @return 文章信息列表
     */
    @GetMapping("nominate/classify")
    public Result<List<Article>> classifyNominate(@RequestParam long articleID){
        return service.getClassifyNominate(articleID);
    }
    /**
     * 内容页同标签推荐获取接口
     * @param articleID 文章id
     * @return 文章信息列表
     */
    @GetMapping("nominate/tag")
    public Result<List<ArticleTagNominate>> tagNominate(@RequestParam long articleID){
        return service.getTagNominate(articleID);
    }

    /**
     * 内容页新增评论接口
     * @param commentForm 评论信息
     * @return 成功
     */
    //需要登录验证
    @PostMapping("comment")
    public Result<String> saveComment(@RequestBody CommentForm commentForm){
        if (commentForm.getComment().isEmpty()){
            return Result.error(CodeMsg.BIND_ERROR.fillArgs("评论不能为空"));
        }else{
            if (commentForm.getComment().length()>1000){
                return Result.error(CodeMsg.BIND_ERROR.fillArgs("评论字数不能超过1000"));
            }
        }
        if (commentForm.getArticleID()<=0) return Result.error(CodeMsg.BIND_ERROR.fillArgs("找不到文章"));
        if (commentForm.getRootID()==-1) commentForm.setRootID(null);
        if (commentForm.getParentID()==-1) commentForm.setParentID(null);
        return service.saveComment(commentForm);
    }
}
