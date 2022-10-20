package com.synergism.blog.api.indexAPI.service;

import com.synergism.blog.api.articleAPI.entity.Pagination;
import com.synergism.blog.core.article.enumeration.ArticleSort;
import com.synergism.blog.core.classify.entity.ClassifyInformation;
import com.synergism.blog.core.tag.entity.TagInformation;
import com.synergism.blog.core.user.entity.UserInformation;
import com.synergism.blog.result.Result;

import java.util.List;

public interface IndexService {
    /**
     * 首页文章列表
     * @param currentPage 当前页
     * @param pageSize 页容量
     * @param articleSort 排序
     * @return 结果[分页]
     */
    Result<Pagination> getIndexArticles(int currentPage, int pageSize, ArticleSort articleSort);

    /**
     * 首页默认用户信息
     * 默认用户一般指向管理用户
     * 用户id一般为1
     * @return 结果[用户信息]
     */
    Result<UserInformation> getIndexUserInfo();

    /**
     * 首页显示的标签云
     * 通过判断传入的用户名是否为空
     * 为空返回全部列表前20个
     * 反之返回用户名下的标签列表前20个
     * @param username 用户名
     * @return 标签列表
     */
    Result<List<TagInformation>> getIndexTag(String username);

    /**
     * 首页显示的分类云
     * 与标签云一致，判断用户名传入是否为空
     * 为空返回全部列表前20个
     * 反之返回用户名下的分类列表前20个
     * @param username 用户名
     * @return 分类列表
     */
    Result<List<ClassifyInformation>> getIndexClassify(String username);
}
