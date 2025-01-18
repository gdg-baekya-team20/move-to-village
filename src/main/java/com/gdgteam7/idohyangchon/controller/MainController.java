package com.gdgteam7.idohyangchon.controller;

import com.gdgteam7.idohyangchon.dto.CostResponseDto;
import com.gdgteam7.idohyangchon.dto.RecommendationRequestDto;
import com.gdgteam7.idohyangchon.dto.RecommendationResponseDto;
import com.gdgteam7.idohyangchon.service.MainService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "API 명세서")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
@CrossOrigin
public class MainController {
    private final MainService mainService;

    @Operation(summary = "결과 확인하기")
    @GetMapping("/relocation/cost")
    public ResponseEntity<CostResponseDto> calculateCost(
            @RequestParam int familyCount,
            @RequestParam int foodCost,
            @RequestParam int transportationCost,
            @RequestParam int housingCost
    ) {
        CostResponseDto responseDto = mainService.calculateCost(familyCount, foodCost, transportationCost, housingCost);
        return ResponseEntity.ok(responseDto);
    }

    @Operation(summary = "이주 지역 추천 받기")
    @PostMapping("/location/recommendation")
    public ResponseEntity<RecommendationResponseDto> recommendRural(
            @RequestBody RecommendationRequestDto request
            ) {
        RecommendationResponseDto responseDto = mainService.recommendRural(request);
        return ResponseEntity.ok(responseDto);
    }
}
