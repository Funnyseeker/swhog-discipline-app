package ru.dimonds.swgoh.model.dto;

import io.hypersistence.utils.hibernate.type.range.PostgreSQLRangeType;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.Type;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class DisciplineRuleDto {
    private Long           id;
    private Long           guild;
    private String         reason;
    private Long           disciplinePoints;
    @Column(columnDefinition = "text")
    private String         botType;
    @Type(PostgreSQLRangeType.class)
    @Column(name = "rule_values")
    private RangeDto<Long> ruleValues;
}
