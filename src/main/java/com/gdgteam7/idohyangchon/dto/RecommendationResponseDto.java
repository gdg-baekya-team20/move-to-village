package com.gdgteam7.idohyangchon.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RecommendationResponseDto {
    @Data
    @NoArgsConstructor
    public static class Score {
        String name;
        Integer score;
    }
    String ruralName;
    String ruralThumbnailUrl;
    Score[] score;
    Integer housingCost;
    Integer foodCost;
    Integer transportationCost;
    String ruralDescription;
    String ruralUrl;
}
