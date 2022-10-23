package com.synergism.blog.core.comment.serviceImpl;

import com.synergism.blog.core.comment.entity.Comment;
import com.synergism.blog.core.comment.entity.CommentInformation;
import com.synergism.blog.core.comment.entity.CommentsChild;
import com.synergism.blog.core.comment.mapper.CommentMapper;
import com.synergism.blog.core.comment.service.CommentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * <p>
 * 评论表 服务实现类
 * </p>
 *
 * @author Synergism
 * @since 2022-10-10 09:53:06
 */
@Service
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment> implements CommentService {

    private final CommentMapper mapper;

    public CommentServiceImpl(CommentMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public List<CommentInformation> getAllCommentInformationListByArticleID(long articleID) {
        return mapper.getAllCommentInformationListByArticleID(articleID);
    }

    @Override
    public List<CommentInformation> getTopListByArticleID(long articleID) {
        //获取文章下全部评论
        List<CommentInformation> allList = this.getAllCommentInformationListByArticleID(articleID);
        //筛选根或子评论列表 根据if(commentInformation.getRootId()==null)
        Map<Boolean, List<CommentInformation>> listMap = allList
                .stream()
                .collect(Collectors.groupingBy(commentInformation -> commentInformation.getRootId() == null));
        //获得根评论
        List<CommentInformation> rootList = listMap.get(true);
        //获得子评论
        List<CommentInformation> childList = listMap.get(false);

        //遍历根评论
        rootList.forEach(
                //为根评论写入子评论
                root -> root.setChildren(
                        //筛选该根评论下的评论
                        childList.stream()
                        .filter(child -> child.getRootId().equals(root.getParentId()))
                                //按点赞数倒序排序
                        .sorted(Comparator.comparing(Comment::getLikeCount).reversed())
                                //获得前三个
                        .limit(3)
                                //转为子评论
                        .map(child -> new CommentsChild(child, ""))

                        .collect(Collectors.toList())
                )
        );
        return rootList;
    }
}
