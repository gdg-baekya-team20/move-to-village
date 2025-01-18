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

        CostResponseDto.BudgetItem[] budgetItems = getRelevantItemOverTime(futureCost - currentCost);

        return new CostResponseDto(currentCost, futureCost, savedPercentage, budgetItems);
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

    private CostResponseDto.BudgetItem[] getRelevantItemOverTime(int savedAmountPerMonth) {
        CostResponseDto.BudgetItem[] items = new CostResponseDto.BudgetItem[3];
        int savedAmountPerYear = savedAmountPerMonth * 12;
        int[] years = {1, 5, 20};
        for(int i = 0; i < 3; i++) {
            items[i] = getRelevantItemByBudget(savedAmountPerYear * years[i]);
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
