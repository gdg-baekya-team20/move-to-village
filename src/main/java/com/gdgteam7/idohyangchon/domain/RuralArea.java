package com.gdgteam7.idohyangchon.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class RuralArea {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String ruralName;

    private Integer housingCost;
    private Integer foodCost;
    private Integer transportationCost;

    private Integer transportationScore;
    private Integer cultureScore;
    private Integer medicalScore;
    private Integer greenScore;
    private Integer costScore;

    private String thumbnailUrl;
    private String localGovernmentUrl;

}
