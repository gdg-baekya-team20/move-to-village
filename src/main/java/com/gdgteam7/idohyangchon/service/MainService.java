package com.gdgteam7.idohyangchon.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gdgteam7.idohyangchon.domain.RuralArea;
import com.gdgteam7.idohyangchon.dto.CostResponseDto;
import com.gdgteam7.idohyangchon.dto.RecommendationRequestDto;
import com.gdgteam7.idohyangchon.dto.RecommendationResponseDto;
import com.gdgteam7.idohyangchon.enums.Priorities;
import com.gdgteam7.idohyangchon.repository.RuralAreaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class MainService {
    private final RuralAreaRepository repository;
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

        CostResponseDto.BudgetItem[] budgetItems = getRelevantItemOverTime(currentCost - futureCost);

        return new CostResponseDto(currentCost, futureCost, savedPercentage, budgetItems);
    }

    public RecommendationResponseDto recommendRural(RecommendationRequestDto request) throws JsonProcessingException {
        // 일단 String을 Enum으로 매핑할까
        String purpose = request.getPurpose();
        Priorities[] priorities = Arrays.stream(request.getPriorities())
                .map(Priorities::valueOf)
                .toArray(Priorities[]::new);

        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, Object> requestMap = new HashMap<>();
        requestMap.put("purpose", purpose);
        requestMap.put("priorities", Arrays.stream(priorities)
                .map(Enum::name)
                .toArray(String[]::new));

        String jsonString2 = objectMapper.writeValueAsString(requestMap);


        // AI 결과 가져오면
        String aiResult = "이주목적: 일자리, 우선순위: 교통, 의료시설, 자연환경, 기타조건: 아이들 교육 중요, 추천지역: 여수, 이유: 여수는 화학·관광 분야 일자리가 있으며, 해양환경과 병원 인프라가 아이 교육에도 적합합니다.";

        String ruralName = null;
        String ruralDescription = null;

        String[] keyValuePairs = aiResult.split(", ");
        for (String pair : keyValuePairs) {
            if (pair.startsWith("추천지역:")) {
                ruralName = pair.substring(pair.indexOf("추천지역:") + 5).trim();
            } else if (pair.startsWith("이유:")) {
                ruralDescription = pair.substring(pair.indexOf("이유:") + 3).trim();
            }
        }

        // 거기서 이름 가져오고
        // 이름으로 DB에서 엔티티 가져와서
        RuralArea ruralArea = repository.findByRuralName(ruralName);

        // 필요한 부분 채우고 리턴
        RecommendationResponseDto responseDto = new RecommendationResponseDto();
        responseDto.setRuralName(ruralArea.getRuralName());
        responseDto.setRuralThumbnailUrl(ruralArea.getThumbnailUrl());
        responseDto.setHousingCost(ruralArea.getHousingCost());
        responseDto.setFoodCost(ruralArea.getFoodCost());
        responseDto.setTransportationCost(ruralArea.getTransportationCost());
        responseDto.setRuralUrl(ruralArea.getLocalGovernmentUrl());

        return responseDto;
    }

    private CostResponseDto.BudgetItem[] getRelevantItemOverTime(int savedAmountPerMonth) {
        CostResponseDto.BudgetItem[] items = new CostResponseDto.BudgetItem[3];
        int savedAmountPerYear = savedAmountPerMonth * 12;

        int[] years = {1, 5, 20};
        for(int i = 0; i < 3; i++) {
            System.out.println("budget: " + savedAmountPerYear * years[i]);
            items[i] = getRelevantItemByBudget(savedAmountPerYear * years[i]);
            System.out.println("-> " + items[i].getName());
        }
        return items;
    }

    private CostResponseDto.BudgetItem getRelevantItemByBudget(int budget) {
        if(budget < 50) return new CostResponseDto.BudgetItem("나이키 에어 조던", "https://jimkanman-bucket.s3.ap-northeast-2.amazonaws.com/baekya-hackathon/shoes.png");
        else if (budget <= 90) return new CostResponseDto.BudgetItem("루이비통 카드지갑", "https://jimkanman-bucket.s3.ap-northeast-2.amazonaws.com/baekya-hackathon/louisvuitton.png");
        else if (budget <= 100) return new CostResponseDto.BudgetItem("iPad Air 11", "https://jimkanman-bucket.s3.ap-northeast-2.amazonaws.com/baekya-hackathon/ipad.png");
        else if (budget <= 140) return new CostResponseDto.BudgetItem("iPad Air 11 풀옵션", "https://jimkanman-bucket.s3.ap-northeast-2.amazonaws.com/baekya-hackathon/ipad.png");
        else if (budget <= 200) return new CostResponseDto.BudgetItem("iPad Pro 13", "https://jimkanman-bucket.s3.ap-northeast-2.amazonaws.com/baekya-hackathon/ipad.png");
        else if (budget <= 300) return new CostResponseDto.BudgetItem("MacBook Pro M4", "https://jimkanman-bucket.s3.ap-northeast-2.amazonaws.com/baekya-hackathon/macbook.png");
        else if (budget <= 600) return new CostResponseDto.BudgetItem("MacBook Pro M4 Max", "https://jimkanman-bucket.s3.ap-northeast-2.amazonaws.com/baekya-hackathon/macbook.png");
        else if (budget <= 1000) return new CostResponseDto.BudgetItem("롤렉스 서브마리너 논데이트 (40mm)", "https://jimkanman-bucket.s3.ap-northeast-2.amazonaws.com/baekya-hackathon/rolex.png");
        else if (budget <= 5000) return new CostResponseDto.BudgetItem("현대자동차 캐스퍼 풀옵션", "https://jimkanman-bucket.s3.ap-northeast-2.amazonaws.com/baekya-hackathon/avante.png");
        else if (budget <= 10000) return new CostResponseDto.BudgetItem("포르쉐 카이만", "https://jimkanman-bucket.s3.ap-northeast-2.amazonaws.com/baekya-hackathon/porsche.png");
        else if (budget <= 20000) return new CostResponseDto.BudgetItem("지방 전원 주택", "https://jimkanman-bucket.s3.ap-northeast-2.amazonaws.com/baekya-hackathon/house.png");
        else return new CostResponseDto.BudgetItem("소형 아파트", "https://jimkanman-bucket.s3.ap-northeast-2.amazonaws.com/baekya-hackathon/aprat.png");
    }

}
