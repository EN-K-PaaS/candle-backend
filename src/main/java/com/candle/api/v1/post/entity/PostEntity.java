package com.candle.api.v1.post.entity;

import com.candle.api.v1.post.dto.PostType;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class PostEntity {

    @Id @GeneratedValue
    private int id;

    @Column(name = "user_id")
    private String userId;

    @Enumerated(EnumType.STRING)
    private PostType type;

    private String title;

    private String content;

    private String image;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    protected PostEntity() {
    }

    public PostEntity(int id, String userId, PostType type, String title, String content, String image, LocalDateTime createdAt) {
        this.id = id;
        this.userId = userId;
        this.type = type;
        this.title = title;
        this.content = content;
        this.image = image;
        this.createdAt = createdAt;
    }

    public int getId() {
        return id;
    }

    public String getUserId() {
        return userId;
    }

    public PostType getType() {
        return type;
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
