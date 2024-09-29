package com.candle.api.v1.post.dto.response;

import java.time.LocalDateTime;

public class DiaryResponse {
    private final Integer id;
    private final String title;
    private final String content;
    private final String image;
    private final LocalDateTime createdAt;

    public DiaryResponse(Integer id, String title, String content, String image, LocalDateTime createdAt) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.image = image;
        this.createdAt = createdAt;
    }

    public Integer getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public String getImage() {
        return image;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}
