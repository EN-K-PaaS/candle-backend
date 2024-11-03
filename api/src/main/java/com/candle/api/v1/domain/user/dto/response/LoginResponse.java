package com.candle.api.v1.domain.user.dto.response;

import jakarta.validation.constraints.NotBlank;

public record LoginResponse(@NotBlank String id, @NotBlank String phoneNumber, String profileImage,
                            @NotBlank String name, String introduction) {
}
