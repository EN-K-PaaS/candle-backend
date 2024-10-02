package com.candle.api.v1.domain.community.entity;

import com.candle.api.v1.domain.user.entity.User;
import jakarta.persistence.*;

@Entity
public class Like {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "community_id")
    private Community community;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    protected Like() {
    }

    public Like(Community community, User user) {
        this.community = community;
        this.user = user;
        community.addLike(this);
    }

    public Integer getId() {
        return id;
    }
}
