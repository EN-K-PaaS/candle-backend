package com.candle.api.v1.domain.comment.controller;

import com.candle.api.v1.domain.comment.dto.request.CommentRequest;
import com.candle.api.v1.domain.comment.dto.request.UpdatedCommentRequest;
import com.candle.api.v1.domain.comment.service.CommentService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/community/comment")
public class CommentController {

    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
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
}
