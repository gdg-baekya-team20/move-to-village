package com.gdgteam7.idohyangchon.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CostResponseDto {
    int currentCost;  // 단위 만원
    int futureCost;  // 단위 만원
    int savedPercentage; // 단위 %
    // 더 필요한 정보
    // - 1년 / 5년 / 10년 단위로 절약액 비유 단위 (명품백/중형차/자동차/...)
    //   -> 프론트엔드에서 계산?
}
