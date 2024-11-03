package com.candle.api.v1.domain.comment.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UpdatedCommentRequest(@NotNull Integer commentId, @NotBlank String content) {
}
