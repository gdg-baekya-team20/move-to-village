package com.gdgteam7.idohyangchon.service;

import com.gdgteam7.idohyangchon.domain.RuralArea;
import com.gdgteam7.idohyangchon.dto.CostResponseDto;
import com.gdgteam7.idohyangchon.dto.RecommendationRequestDto;
import com.gdgteam7.idohyangchon.dto.RecommendationResponseDto;
import com.gdgteam7.idohyangchon.enums.Priorities;
import com.gdgteam7.idohyangchon.repository.RuralAreaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
@RequiredArgsConstructor
public class MainService {
    RuralAreaRepository repository;
    public CostResponseDto calculateCost(int familyCount, int foodCost, int transportationCost, int housingCost) {
        // 랜덤 지역 + 서울 가져옴
        RuralArea area = repository.findRandomExcludingSuch("서울특별시");
        RuralArea seoul = repository.findByRuralName("서울특별시");

        // 절감할 가격 계산 (서울과 해당 지역의 물가 비율 계산하여 곱함)
        int currentCost = foodCost + transportationCost + housingCost;
        float scalingFactor = (float) (area.getFoodCost() + area.getTransportationCost() + area.getHousingCost()) / (seoul.getFoodCost() + seoul.getTransportationCost() + seoul.getHousingCost());
        int futureCost = (int) (currentCost * scalingFactor);

        // 할인율 계산
        int savedPercentage = (int) ((float) (currentCost - futureCost) / currentCost * 100);

        return new CostResponseDto(currentCost, futureCost, savedPercentage);
    }

    public RecommendationResponseDto recommendRural(RecommendationRequestDto request) {
        // 일단 String을 Enum으로 매핑할까
        String purpose = request.getPurpose();
        Priorities[] priorities = Arrays.stream(request.getPriorities())
                .map(Priorities::valueOf)
                .toArray(Priorities[]::new);

        // AI 결과 가져오면


        // 거기서 이름 가져오고
        // 이름으로 DB에서 엔티티 가져와서
        // 필요한 부분 채우고 리턴



        return new RecommendationResponseDto();
    }
}
