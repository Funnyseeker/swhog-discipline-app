package ru.dimonds.swgoh.model.dto;

import lombok.*;

import java.time.OffsetDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PlayerDisciplineHistoryDto {
    private Long           id;
    private OffsetDateTime date;
    private Long           playerId;
    private Long           ruleId;
    private Long           disciplinePoints;
}
