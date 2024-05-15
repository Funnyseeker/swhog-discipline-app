package ru.dimonds.swgoh.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PlayerDisciplineHistoryDto {
    private Long           id;
    private OffsetDateTime date;
    private Long           player;
    private String         reason;
    private Long           disciplinePoints;
}
