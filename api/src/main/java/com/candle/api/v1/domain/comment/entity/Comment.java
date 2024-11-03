package com.candle.api.v1.domain.comment.entity;

import com.candle.api.v1.domain.community.entity.Community;
import com.candle.api.v1.domain.user.entity.User;
import com.candle.api.v1.global.embeddable.Post;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Comment {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "community_id", nullable = false)
    private Community community;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @OneToMany(mappedBy = "comment")
    private List<CommentLike> commentLikes = new ArrayList<>();

    @Embedded
    private Post post;

    protected Comment() {
    }

    public Comment(Community community, User user, String content, String image) {
        this.post = new Post(content, LocalDateTime.now());
        this.community = community;
        this.user = user;
        community.addComment(this);
    }

    public Integer getId() {
        return id;
    }

    public Post getPost() {
        return post;
    }

    public User getUser() {
        return user;
    }

    public List<CommentLike> getLikes() {
        return commentLikes;
    }

    public void addLike(CommentLike commentLike) {
        commentLikes.add(commentLike);
    }
}
