package com.candle.api.v1.domain.comment.service;

import com.candle.api.v1.domain.comment.dto.request.CommentDTO;
import com.candle.api.v1.domain.comment.dto.request.UpdatedCommentRequest;
import com.candle.api.v1.domain.comment.entity.Comment;
import com.candle.api.v1.domain.comment.repository.CommentRepository;
import com.candle.api.v1.domain.community.entity.Community;
import com.candle.api.v1.domain.community.service.CommunityService;
import com.candle.api.v1.domain.user.entity.User;
import com.candle.api.v1.domain.user.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CommentService {

    private final CommentRepository commentRepository;
    private final CommunityService communityService;
    private final UserService userService;

    public CommentService(CommentRepository commentRepository, CommunityService communityService, UserService userService) {
        this.commentRepository = commentRepository;
        this.communityService = communityService;
        this.userService = userService;
    }

    @Transactional
    public Integer writingComment(CommentDTO commentDTO) {
        Integer commentId = commentDTO.communityId();
        String userId = commentDTO.userId();
        String content = commentDTO.content();
        String image = commentDTO.image();

//        if (commentRepository.existsByCommunityIdAndUserId(commentId, userId)) {
//            throw new IllegalArgumentException("이미 댓글을 작성한 사용자입니다.");
//        }

        Community community = communityService.findById(commentId);
        User user = userService.findById(userId);

        Comment comment = new Comment(community, user, content, image);
        commentRepository.save(comment);
        return comment.getId();
    }

    @Transactional
    public Integer deleteComment(Integer commentId) {
        if (!commentRepository.existsById(commentId)) {
            throw new IllegalArgumentException("존재하지 않는 댓글입니다.");
        }

        commentRepository.deleteById(commentId);
        return commentId;
    }

    @Transactional
    public Integer updateComment(UpdatedCommentRequest commentRequest) {
        if (!commentRepository.existsById(commentRequest.commentId())) {
            throw new IllegalArgumentException("존재하지 않는 댓글입니다.");
        }

        Comment comment = commentRepository.findById(commentRequest.commentId()).get();
        comment.getPost().setContent(commentRequest.content());
        return comment.getId();
    }

    public Comment getComment(Integer commentId) {
        return commentRepository.findById(commentId).orElseThrow(() -> new IllegalArgumentException("존재하지 않는 댓글입니다."));
    }

    public Comment findById(Integer commentId) {
        return commentRepository.findById(commentId).orElseThrow(() -> new IllegalArgumentException("존재하지 않는 댓글입니다."));
    }
}
