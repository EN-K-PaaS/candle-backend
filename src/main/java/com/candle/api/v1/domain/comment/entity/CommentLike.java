package com.candle.api.v1.domain.comment.entity;

import com.candle.api.v1.domain.user.entity.User;
import jakarta.persistence.*;

@Entity
public class CommentLike {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "comment_id", nullable = false)
    private Comment comment;

    protected CommentLike() {
    }

    public CommentLike(User user, Comment comment) {
        this.user = user;
        this.comment = comment;
        comment.addLike(this);
    }

    public Integer getId() {
        return id;
    }
}
