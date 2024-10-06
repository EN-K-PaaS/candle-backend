package com.candle.api.v1.domain.diary.entity;

import com.candle.api.v1.domain.user.entity.User;
import com.candle.api.v1.global.embeddable.Post;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class Diary {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Embedded
    private Post post;

    protected Diary() {
    }

    public Diary(User user, String content, String image, LocalDateTime createdAt) {
        this.user = user;
        this.post = new Post(content, image, createdAt);
    }

    public Integer getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public Post getPost() {
        return post;
    }
}
