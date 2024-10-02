package com.candle.api.v1.domain.community.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record LikeRequest(@NotNull Integer communityId, @NotBlank String userId) {
}
