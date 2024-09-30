package com.candle.api.v1.post.entity;

import com.candle.api.v1.post.dto.PostType;
import com.candle.api.v1.user.entity.User;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class Post {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PostType type;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String content;

    private String image;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    protected Post() {
    }

    public Post(User user, PostType type, String title, String content, String image, LocalDateTime createdAt) {
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

    public void setTitle(String title) {
        this.title = title;
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
