package com.aces.bird.ranking.repository;

import com.aces.bird.ranking.model.dto.UserDto;
import com.aces.bird.ranking.model.entity.Score;
import com.aces.bird.ranking.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
    // User 엔티티와 관련된 데이터베이스 연산을 처리하는 메소드를 작성합니다.

    User findByUserId(String userId);
    User findByUsername(String username);
    User findByEmail(String email);

    List<Score> findAllByUserId(String userId);

    List<UserDto> getRanking();

}
