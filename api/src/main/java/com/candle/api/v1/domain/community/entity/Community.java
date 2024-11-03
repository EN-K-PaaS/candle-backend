package com.candle.api.v1.domain.community.entity;

import com.candle.api.v1.domain.comment.entity.Comment;
import com.candle.api.v1.domain.user.entity.User;
import com.candle.api.v1.global.embeddable.Post;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Community {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @OneToMany(mappedBy = "community")
    private List<Comment> comments = new ArrayList<>();

    @OneToMany(mappedBy = "community")
    private List<CommunityLike> communityLike;

    @Column(nullable = false)
    private String title;

    @Embedded
    private Post post;

    protected Community() {
    }

    public Community(User user, String title, String content, String image, LocalDateTime createdAt) {
        this.user = user;
        this.title = title;
        this.post = new Post(content, image, createdAt);
    }

    public Integer getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public String getTitle() {
        return title;
    }

    public Post getPost() {
        return post;
    }

    public List<CommunityLike> getLike() {
        return communityLike;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void addComment(Comment comment) {
        this.comments.add(comment);
    }

    public void addLike(CommunityLike communityLike) {
        this.communityLike.add(communityLike);
    }
}
