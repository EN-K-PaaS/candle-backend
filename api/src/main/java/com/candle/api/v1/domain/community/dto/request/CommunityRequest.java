package com.candle.api.v1.domain.community.dto.request;

import jakarta.validation.constraints.NotBlank;

public record CommunityRequest(@NotBlank String userId, @NotBlank String title, @NotBlank String content, String image) {
}
