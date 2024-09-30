package com.candle.api.v1.post.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record LikeRequest(@NotNull Integer id, @NotBlank String userId) {
}
