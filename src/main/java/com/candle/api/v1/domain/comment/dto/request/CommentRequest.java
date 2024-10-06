package com.candle.api.v1.domain.comment.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CommentRequest(@NotNull Integer communityId, @NotBlank String userId, @NotBlank String content, String image) {
}
