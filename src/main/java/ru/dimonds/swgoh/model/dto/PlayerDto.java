package ru.dimonds.swgoh.model.dto;

import lombok.*;

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
    private Set<PlayerDisciplineHistoryDto> playerDisciplineHistory;
}