package com.candle.api.v1.domain.comment.service;

import com.candle.api.v1.domain.comment.dto.request.CommentLikeRequest;
import com.candle.api.v1.domain.comment.entity.Comment;
import com.candle.api.v1.domain.comment.entity.CommentLike;
import com.candle.api.v1.domain.comment.repository.CommentLikeRepository;
import com.candle.api.v1.domain.user.entity.User;
import com.candle.api.v1.domain.user.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CommentLikeService {

    private final CommentLikeRepository commentLikeRepository;
    private final CommentService commentService;
    private final UserService userService;

    public CommentLikeService(CommentLikeRepository commentLikeRepository, CommentService commentService, UserService userService) {
        this.commentLikeRepository = commentLikeRepository;
        this.commentService = commentService;
        this.userService = userService;
    }

    @Transactional
    public Integer likeComment(CommentLikeRequest commentLikeRequest) {
        Integer commentId = commentLikeRequest.commentId();
        String userId = commentLikeRequest.userId();

//        if (commentLikeRepository.existsByCommunityIdAndUserId(commentId, userId)) {
//            throw new IllegalArgumentException("이미 좋아요를 누른 사용자입니다.");
//        }

        Comment comment = commentService.findById(commentId);
        User user = userService.findById(userId);
        CommentLike commentLike = new CommentLike(user, comment);

        commentLikeRepository.save(commentLike);
        return commentLike.getId();
    }

    @Transactional
    public Integer unLikeComment(Integer commentId) {
        CommentLike commentLike = commentLikeRepository.findById(commentId)
                .orElseThrow(() -> new IllegalArgumentException("좋아요를 누르지 않은 사용자입니다."));

        commentLikeRepository.delete(commentLike);
        return commentLike.getId();
    }
}
