package com.candle.api.v1.dummy;

import com.candle.api.v1.post.entity.Post;
import com.candle.api.v1.user.entity.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
public class DummyService {

    @PersistenceContext
    private final EntityManager entityManager;

    public DummyService(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Transactional
    public void generateUser() {
        User user1 = new User("SeoSangHyeok", "1234", "010-1234-5678", "서상혁");
        User user2 = new User("KwonOhSung", "1234", "010-1234-5678", "권오성");

        entityManager.persist(user1);
        entityManager.persist(user2);
    }

    @Transactional
    public void generateDiary() {
        User user = entityManager.find(User.class, "SeoSangHyeok");
        Post post1 = new Post(user, PostType.DIARY, "오늘은 날씨가 좋다.", "정말 좋다.", null, LocalDateTime.now());

        entityManager.persist(post1);
    }
}
