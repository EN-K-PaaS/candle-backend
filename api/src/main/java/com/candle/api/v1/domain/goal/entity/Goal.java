package com.candle.api.v1.domain.goal.entity;

import com.candle.api.v1.domain.user.entity.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDateTime;

@Entity
public class Goal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(nullable = false)
    private String title;

    @Column(name = "is_finished", nullable = false)
    private boolean isFinished;

    @Column(name = "goal_date", nullable = false)
    private LocalDateTime goalDate;

    @Column(nullable = false)
    private Double progress;

    protected Goal() {
    }

    public Goal(String title, User user, Double progress, LocalDateTime goalDate) {
        this.title = title;
        this.user = user;
        this.isFinished = false;
        this.progress = progress;
        this.goalDate = goalDate;
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

    public boolean isFinished() {
        return isFinished;
    }

    public Double getProgress() {
        return progress;
    }

    public LocalDateTime getGoalDate() {
        return goalDate;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setFinished(boolean finished) {
        isFinished = finished;
    }

    public void setGoalDate(LocalDateTime goalDate) {
        this.goalDate = goalDate;
    }

    public void setProgress(Double progress) {
        this.progress = progress;
    }
}
