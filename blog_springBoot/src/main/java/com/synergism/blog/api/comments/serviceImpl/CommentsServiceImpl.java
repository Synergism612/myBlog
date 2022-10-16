package com.synergism.blog.api.comments.serviceImpl;

import com.synergism.blog.core.comment.entity.CommentInformation;
import com.synergism.blog.api.comments.service.CommentsService;
import com.synergism.blog.core.comment.service.CommentService;
import com.synergism.blog.result.entity.Result;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentsServiceImpl implements CommentsService {

    private final CommentService commentService;

    public CommentsServiceImpl(CommentService commentService) {
        this.commentService = commentService;
    }

    @Override
    public Result<List<CommentInformation>> getIndexComments() {
        List<CommentInformation> result = commentService.getAllComments()
                .stream()
                .sorted(Comparator.comparing(CommentInformation::getLikeCount).reversed())
                .collect(Collectors.toList());
        if (result.size() > 5)
            result = result.subList(0, 5);
        return Result.success(result);
    }
}
