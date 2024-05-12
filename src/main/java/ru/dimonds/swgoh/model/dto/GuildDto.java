package ru.dimonds.swgoh.model.dto;

import lombok.*;

import java.time.OffsetDateTime;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GuildDto {
    private Long           id;
    private String         guild_url;
    private String         name;
    private OffsetDateTime lastSyncDate;
    private Set<Long>      userList;
}