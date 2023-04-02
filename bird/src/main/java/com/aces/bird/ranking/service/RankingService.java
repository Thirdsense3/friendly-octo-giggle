package com.aces.bird.ranking.service;

import com.aces.bird.ranking.model.dto.RankingDto;

import java.util.List;

public interface RankingService {
    List<RankingDto> getRankingList();

    List<RankingDto> getRankingListByUser(String userId);

    RankingDto createNewScore(String userId, int scoreValue);
}
