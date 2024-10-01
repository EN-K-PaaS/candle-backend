package com.candle.api.v1.domain.community.dto.response;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record CommunityResponse(@NotBlank String userId, @NotBlank String userName, @NotNull Integer id, @NotBlank String title, @NotBlank String content, String image,
                                @NotBlank LocalDateTime createdAt, Integer likeCount) {
}
