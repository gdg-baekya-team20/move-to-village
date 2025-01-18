package com.gdgteam7.idohyangchon.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RecommendationRequestDto {
    String purpose;
    String[] priorities;
    String additionalConditions;
}
