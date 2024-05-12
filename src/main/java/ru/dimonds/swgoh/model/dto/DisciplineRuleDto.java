package ru.dimonds.swgoh.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DisciplineRuleDto {
    private Long   id;
    private Long   guild;
    private String reason;
    private Long   disciplinePoints;
}
