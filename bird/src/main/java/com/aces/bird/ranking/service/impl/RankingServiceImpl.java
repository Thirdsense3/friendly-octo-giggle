package com.aces.bird.ranking.service.impl;

import com.aces.bird.ranking.model.dto.RankingDto;
import com.aces.bird.ranking.model.entity.Score;
import com.aces.bird.ranking.model.entity.User;
import com.aces.bird.ranking.repository.ScoreRepository;
import com.aces.bird.ranking.repository.UserRepository;
import com.aces.bird.ranking.service.RankingService;
import com.aces.bird.ranking.service.exception.ResourceNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public class RankingServiceImpl implements RankingService {
    private final UserRepository userRepository;
    private final ScoreRepository scoreRepository;

    @Override
    public List<RankingDto> getRankingList() {
        List<Score> scoreList = scoreRepository.findAll(Sort.by(Sort.Direction.DESC, "score"));

        List<RankingDto> rankingDtoList = new ArrayList<>();

        for (Score score : scoreList) {
            User user = score.getUser();
            rankingDtoList.add(new RankingDto(user.getUserId(), score.getScore()));
        }

        return rankingDtoList;
    }

    @Override
    public List<RankingDto> getRankingListByUser(String userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found: " + userId));

        List<Score> scoreList = scoreRepository.findAllByUser(user, Sort.by(Sort.Direction.DESC, "score"));

        List<RankingDto> rankingDtoList = new ArrayList<>();

        for (Score score : scoreList) {
            rankingDtoList.add(new RankingDto(userId, score.getScore()));
        }

        return rankingDtoList;
    }

    @Override
    @Transactional
    public RankingDto createNewScore(String userId, int scoreValue) {
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException(userId));

        // Create new score with current time
        LocalDateTime currentTime = LocalDateTime.now();
        Score newScore = new Score(user, scoreValue, currentTime);
        scoreRepository.save(newScore);

        // Update user's high score if the new score is higher
        if (user.getHighScore() < scoreValue) {
            user.setHighScore(scoreValue);
            userRepository.save(user);
        }

        // Get ranking
        List<User> rankingUsers = userRepository.getRanking();

        // Get user's rank
        int userRank = rankingUsers.indexOf(user) + 1;

        return new RankingDto(user.getUserId(), userRank);
    }

}
