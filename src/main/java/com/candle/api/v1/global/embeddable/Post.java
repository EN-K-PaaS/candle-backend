package com.candle.api.v1.global.embeddable;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.time.LocalDateTime;

@Embeddable
public class Post {

    @Column(nullable = false)
    private String content;

    private String image;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    public Post() {
    }

    public Post(String content, LocalDateTime createdAt) {
        this.content = content;
        this.createdAt = createdAt;
    }

    public Post(String content, String image, LocalDateTime createdAt) {
        this.content = content;
        this.image = image;
        this.createdAt = createdAt;
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

    public void setContent(String content) {
        this.content = content;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
