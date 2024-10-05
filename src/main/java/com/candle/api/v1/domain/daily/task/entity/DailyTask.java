package com.candle.api.v1.domain.daily.task.entity;

import com.candle.api.v1.domain.user.entity.User;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class DailyTask {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private Integer rank;

    @Column(name = "is_finished", nullable = false)
    private boolean isFinished;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    protected DailyTask() {}

    public DailyTask(String title, Integer rank, boolean isFinished, LocalDateTime createdAt) {
        this.title = title;
        this.rank = rank;
        this.isFinished = isFinished;
        this.createdAt = createdAt;
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

    public Integer getRank() {
        return rank;
    }

    public boolean isFinished() {
        return isFinished;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void changeFinished() {
        this.isFinished = !this.isFinished;
    }
}
