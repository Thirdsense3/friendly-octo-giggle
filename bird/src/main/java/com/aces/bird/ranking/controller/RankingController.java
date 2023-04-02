package com.aces.bird.ranking.controller;

import com.aces.bird.ranking.model.dto.RankingDto;
import com.aces.bird.ranking.service.RankingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/rankings")
@RequiredArgsConstructor
class RankingController {

    private final RankingService rankingService;

    // 모든 랭킹 정보 조회
    @GetMapping
    public ResponseEntity<List<RankingDto>> getAllRankings() {
        List<RankingDto> rankings = rankingService.getRankingList();
        return ResponseEntity.ok(rankings);
    }

    // 랭킹 등록
    @PostMapping
    public ResponseEntity<RankingDto> updateRanking(@RequestBody RankingDto rankingDto) {
        RankingDto createdRanking = rankingService.createNewScore(rankingDto.getUserId(), rankingDto.getScore());
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(createdRanking.getUserId())
                .toUri();
        return ResponseEntity.created(location).body(createdRanking);
    }

}
