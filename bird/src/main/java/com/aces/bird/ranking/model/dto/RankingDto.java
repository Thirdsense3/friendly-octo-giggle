package com.aces.bird.ranking.model.dto;

import lombok.*;

@Getter @Setter @Builder
@NoArgsConstructor
@AllArgsConstructor
public class RankingDto {
    private String userId;
    private Integer score;
}
