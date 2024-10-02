package com.candle.api.v1.domain.comment.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CommentLikeRequest(@NotNull Integer commentId, @NotBlank String userId) {
}
