package ru.dimonds.swgoh.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class DisciplineRuleDto {
    private Long   id;
    private Long   guild;
    private String reason;
    private Long   disciplinePoints;
}
