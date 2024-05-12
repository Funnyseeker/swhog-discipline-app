package ru.dimonds.swgoh.model.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DisciplineRuleDto {
    private Long   id;
    private String reason;
    private Long   disciplinePoints;
}
