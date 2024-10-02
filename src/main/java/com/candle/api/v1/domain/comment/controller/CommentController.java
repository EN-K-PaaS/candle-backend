package com.candle.api.v1.domain.comment.controller;

import com.candle.api.v1.domain.comment.dto.request.CommentLikeRequest;
import com.candle.api.v1.domain.comment.dto.request.CommentRequest;
import com.candle.api.v1.domain.comment.dto.request.UpdatedCommentRequest;
import com.candle.api.v1.domain.comment.entity.Comment;
import com.candle.api.v1.domain.comment.service.CommentLikeService;
import com.candle.api.v1.domain.comment.service.CommentService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/community/comment")
public class CommentController {

    private final CommentService commentService;
    private final CommentLikeService commentLikeService;

    public CommentController(CommentService commentService, CommentLikeService commentLikeService) {
        this.commentService = commentService;
        this.commentLikeService = commentLikeService;
    }

    @PostMapping("/")
    public Integer writingComment(@RequestBody CommentRequest commentRequest) {
        return commentService.writingComment(commentRequest);
    }

    @PutMapping("/")
    public Integer updateComment(@RequestBody UpdatedCommentRequest commentRequest) {
        return commentService.updateComment(commentRequest);
    }

    @DeleteMapping("/")
    public Integer deleteComment(@RequestParam Integer commentId) {
        return commentService.deleteComment(commentId);
    }

    @GetMapping("/")
    public Comment getComment(@RequestParam Integer commentId) {
        return commentService.getComment(commentId);
    }

    @PostMapping("/like")
    public Integer likeComment(@RequestBody CommentLikeRequest commentLikeRequest) {
        return commentLikeService.likeComment(commentLikeRequest);
    }

    @DeleteMapping("/like")
    public Integer unLikeComment(@RequestParam Integer commentId) {
        return commentLikeService.unLikeComment(commentId);
    }
}
