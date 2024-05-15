package ru.dimonds.swgoh.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PlayerDto {
    private Long                            id;
    private String                          name;
    private String                          discordNickName;
    private String                          swgohAllyCode;
    private Long                            disciplinePointsTotal;
    private Set<PlayerDisciplineHistoryDto> playerDisciplineHistory;
}