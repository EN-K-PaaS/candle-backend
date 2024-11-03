package com.candle.api.v1.domain.comment.controller;

import com.candle.api.v1.domain.comment.dto.request.CommentLikeRequest;
import com.candle.api.v1.domain.comment.dto.request.CommentDTO;
import com.candle.api.v1.domain.comment.dto.request.UpdatedCommentRequest;
import com.candle.api.v1.domain.comment.entity.Comment;
import com.candle.api.v1.domain.comment.service.CommentLikeService;
import com.candle.api.v1.domain.comment.service.CommentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/community/comments")
@Tag(name = "Comment", description = "댓글 관련 API")
public class CommentController {

    private final CommentService commentService;
    private final CommentLikeService commentLikeService;

    public CommentController(CommentService commentService, CommentLikeService commentLikeService) {
        this.commentService = commentService;
        this.commentLikeService = commentLikeService;
    }

    @Operation(summary = "댓글 작성", description = "댓글을 작성합니다.")
    @PostMapping
    public Integer writingComment(@RequestBody CommentDTO commentDTO) {
        return commentService.writingComment(commentDTO);
    }

    @Operation(summary = "댓글 수정", description = "댓글을 수정합니다.")
    @PutMapping
    public Integer updateComment(@RequestBody UpdatedCommentRequest commentRequest) {
        return commentService.updateComment(commentRequest);
    }

    @Operation(summary = "댓글 삭제", description = "댓글을 삭제합니다.")
    @DeleteMapping("/{id}")
    public Integer deleteComment(@PathVariable Integer id) {
        return commentService.deleteComment(id);
    }

    @Operation(summary = "댓글 조회", description = "댓글을 조회합니다.")
    @GetMapping("/{id}")
    public Comment getComment(@PathVariable Integer id) {
        return commentService.getComment(id);
    }

    @Operation(summary = "댓글 좋아요", description = "댓글에 좋아요를 누릅니다.")
    @PostMapping("/like")
    public Integer likeComment(@RequestBody CommentLikeRequest commentLikeRequest) {
        return commentLikeService.likeComment(commentLikeRequest);
    }

    @Operation(summary = "댓글 좋아요 취소", description = "댓글에 좋아요를 취소합니다.")
    @DeleteMapping("/like/{id}")
    public Integer unLikeComment(@PathVariable Integer id) {
        return commentLikeService.unLikeComment(id);
    }
}
