package com.gdgteam7.idohyangchon.service;

import com.gdgteam7.idohyangchon.dto.CostResponseDto;
import com.gdgteam7.idohyangchon.dto.RecommendationRequestDto;
import com.gdgteam7.idohyangchon.dto.RecommendationResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MainService {

    public CostResponseDto calculateCost(int familyCount, int foodCost, int transportationCost, int housingCost) {
        // todo
        return null;
    }

    public RecommendationResponseDto recommendRural(RecommendationRequestDto request) {
        // todo
        return null;
    }
}
