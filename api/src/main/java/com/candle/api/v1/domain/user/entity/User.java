package com.candle.api.v1.domain.user.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class User {
    @Id
    @Column(name = "user_id")
    private String id;

    @Column(columnDefinition = "varchar(20)", nullable = false)
    private String password;

    @Column(columnDefinition = "varchar(20)", nullable = false)
    private String phoneNumber;

    @Column
    private String profileImage;

    @Column(columnDefinition = "varchar(20)", nullable = false)
    private String name;

    @Column(columnDefinition = "varchar(200)")
    private String introduction;

    protected User() {
    }

    public User(String id, String password, String phoneNumber, String name) {
        this.id = id;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.name = name;
    }

    public User(String id, String password, String phoneNumber, String profileImage, String name, String introduction) {
        this.id = id;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.profileImage = profileImage;
        this.name = name;
        this.introduction = introduction;
    }

    public String getId() {
        return id;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getProfileImage() {
        return profileImage;
    }

    public String getName() {
        return name;
    }

    public String getIntroduction() {
        return introduction;
    }
}
