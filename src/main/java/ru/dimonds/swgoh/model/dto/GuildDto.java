package ru.dimonds.swgoh.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GuildDto {
    private Long           id;
    private String         url;
    private String         name;
    private OffsetDateTime lastSyncDate;
    private Set<Long>      userList;
}