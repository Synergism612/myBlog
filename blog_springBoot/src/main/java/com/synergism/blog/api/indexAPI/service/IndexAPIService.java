package com.synergism.blog.api.indexAPI.service;

import com.synergism.blog.core.article.entity.Pagination;
import com.synergism.blog.core.article.enumeration.ArticleSort;
import com.synergism.blog.core.classify.entity.ClassifyInformation;
import com.synergism.blog.core.tag.entity.TagInformation;
import com.synergism.blog.core.user.entity.UserInformation;
import com.synergism.blog.result.Result;

import java.util.List;

public interface IndexAPIService {
    /**
     * 首页默认用户信息
     * 默认用户一般指向管理用户
     * 用户id一般为1
     * @return 结果[用户信息]
     */
    Result<UserInformation> getIndexUserInfo();

    /**
     * 首页显示的标签云、
     * @return 标签列表
     */
    Result<List<TagInformation>> getIndexTag();

    /**
     * 首页显示的分类云
     * @return 分类列表
     */
    Result<List<ClassifyInformation>> getIndexClassify();

    /**
     * 首页文章获取接口
     * @param currentPage 页数
     * @param pageSize 页容
     * @param articleSort 排序
     * @param username 账号
     * @return 分页后的文章信息
     */
    Result<Pagination> getArticle(int currentPage, int pageSize, ArticleSort articleSort, String username);

    /**
     * 删除文章
     * @param username 账号
     * @param articleIDList 文章id列表
     * @return 成功
     */
    Result<String> removeArticle(String username,List<Long> articleIDList);
}
