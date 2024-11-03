package com.candle.api.v1.domain.community.dto.response;

import com.candle.api.v1.domain.comment.dto.request.CommentDTO;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.util.List;

public record CommunityResponse(@NotBlank String userId, @NotBlank String userName, @NotNull Integer id,
                                @NotBlank String title, @NotBlank String content, String image,
                                @NotBlank LocalDateTime createdAt, Integer likeCount,
                                List<CommentDTO> comments) {
}
