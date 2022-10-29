package com.synergism.blog.core.comment.serviceImpl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.synergism.blog.api.contentAPI.entity.AddComment;
import com.synergism.blog.core.comment.entity.Comment;
import com.synergism.blog.core.comment.entity.CommentInformation;
import com.synergism.blog.core.comment.entity.CommentChild;
import com.synergism.blog.core.comment.entity.CommentParent;
import com.synergism.blog.core.comment.mapper.CommentMapper;
import com.synergism.blog.core.comment.service.CommentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.synergism.blog.result.Result;
import com.synergism.blog.utils.TypeUtil;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
    public List<CommentParent> getAllListByArticleID(long articleID) {
        //获取文章下全部评论
        List<CommentInformation> allList = this.getAllCommentInformationListByArticleID(articleID);

        if (allList.size() == 0) return null;

        //筛选根或子评论列表 根据if(commentInformation.getRootId()==null)
        Map<Boolean, List<CommentInformation>> listMap = allList
                .stream()
                .collect(Collectors.groupingBy(commentInformation -> commentInformation.getRootId() == null));
        //获得根评论
        List<CommentInformation> rootList = listMap.get(true);
        //获得子评论
        List<CommentInformation> childList = listMap.get(false);

        //根评论是父评论，而子评论还需要判断是否是回复子评论的子评论，如
        //A:真不错 -> 文章的一个根评论，父评论
        //  B:确实不错 -> 将作为A的评论的下属子评论，是回复父评论的评论
        //  A: 回复@B 是吧，很不错吧 -> 该子评论作为父评论的子评论同时还是子评论的回复，所以需要一个被回复者昵称作为标识
        return rootList.stream().map(root -> {
            if (childList==null){
                return new CommentParent(root, null, 0);
            }
            List<CommentChild> commentChildList =
                    childList.stream()
                            //筛选该根评论下的所有子评论
                            .filter(child -> child.getRootId().equals(root.getId()))
                            //转为子评论
                            .map(child -> {
                                CommentChild commentChild = new CommentChild(child, "");
                                //筛选出存在回复的子评论
                                childList.forEach(childParent -> {
                                    //根据子评论的父评论id填充被回复者昵称和账号
                                    if (childParent.getId().equals(child.getParentId())) {
                                        commentChild.setParentUserName(childParent.getUsername());
                                        commentChild.setParentNickname(childParent.getNickname());
                                    }
                                });
                                return commentChild;
                            })
                            .sorted(Comparator.comparing(Comment::getCreationTime)).collect(Collectors.toList());
            //将根评论封装为父评论
            return new CommentParent(root, commentChildList, commentChildList.size());
        })
                .collect(Collectors.toList());
    }

    @Override
    public boolean save(AddComment addComment,long userID) {
        Comment comment = new Comment(addComment.getComment(),addComment.getRootID(),addComment.getParentID());
        mapper.insert(comment);
        long commentID = comment.getId();
        try {
            mapper.addComment(commentID,addComment.getArticleID(),userID);
            return true;
        }catch (Exception e){
            mapper.delete(new LambdaQueryWrapper<Comment>().eq(Comment::getId,commentID));
        }
        return false;
    }

    @Override
    public boolean isExist(Long commentID) {
        return this.getOne(new LambdaQueryWrapper<Comment>().eq(Comment::getId,commentID))!=null;
    }
}
