package com.gdgteam7.idohyangchon.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CostResponseDto {
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class BudgetItem {
        String name;
        String imageUrl;
    }

    int currentCost;  // 단위 만원
    int futureCost;  // 단위 만원
    int savedPercentage; // 단위 %
    BudgetItem[] budgetItems;
}
