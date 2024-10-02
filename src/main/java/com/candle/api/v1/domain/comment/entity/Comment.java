package com.candle.api.v1.domain.comment.entity;

import com.candle.api.v1.domain.community.entity.Community;
import jakarta.persistence.*;

@Entity
public class Comment {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "community_id")
    private Community community;

    @Column(nullable = false)
    private String content;

    @Column(name = "like_count")
    private Integer likeCount = 0;

    protected Comment() {
    }

    public Comment(Community community, String content) {
        this.content = content;
        this.community = community;
        community.addComment(this);
    }

    public Integer getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

    public Integer getLikeCount() {
        return likeCount;
    }

    public void increaseLikeCount() {
        likeCount++;
    }
}
