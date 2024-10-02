package com.candle.api.v1.domain.comment.entity;

import com.candle.api.v1.domain.community.entity.Community;
import com.candle.api.v1.domain.user.entity.User;
import com.candle.api.v1.global.embeddable.Post;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class Comment {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "community_id")
    private Community community;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Embedded
    private Post post;

    protected Comment() {
    }

    public Comment(Community community, User user, String content) {
        this.post = new Post(content, LocalDateTime.now());
        this.community = community;
        this.user = user;
        community.addComment(this);
    }

    public Comment(Community community, String content, String image) {
        this.post = new Post(content,image, LocalDateTime.now());
        this.community = community;
        community.addComment(this);
    }

    public Integer getId() {
        return id;
    }

    public Post getPost() {
        return post;
    }
}
