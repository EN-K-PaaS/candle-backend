package com.candle.api.v1.post.entity;

import com.candle.api.v1.post.dto.PostType;
import com.candle.api.v1.user.entity.User;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class Post {

    @Id @GeneratedValue
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Enumerated(EnumType.STRING)
    private PostType type;

    private String title;

    private String content;

    private String image;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    protected Post() {
    }

    public Post(Integer id, User user, PostType type, String title, String content, String image, LocalDateTime createdAt) {
        this.id = id;
        this.user = user;
        this.type = type;
        this.title = title;
        this.content = content;
        this.image = image;
        this.createdAt = createdAt;
    }

    public Integer getId() {
        return id;
    }

    public User getUser() {
        return user;
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
