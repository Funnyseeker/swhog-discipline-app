package ru.dimonds.swgoh.schedule;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import ru.dimonds.swgoh.model.dto.GuildDto;
import ru.dimonds.swgoh.model.dto.PlayerDto;
import ru.dimonds.swgoh.service.GuildService;
import ru.dimonds.swgoh.service.PlayerService;
import ru.dimonds.swgoh.service.SwgohDataService;
import ru.dimonds.swgoh.service.impl.PlayerDisciplineHistoryService;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Component
@Slf4j
public class GuildDataUpdate {

    @Autowired
    private SwgohDataService               dataService;
    @Autowired
    private GuildService                   guildService;
    @Autowired
    private PlayerService                  playerService;
    @Autowired
    private PlayerDisciplineHistoryService disciplineHistoryService;

    @Scheduled(fixedDelay = 1, timeUnit = TimeUnit.HOURS)
    public void updateData() {
        log.info("Starting guild update");
        for (GuildDto guildDto : guildService.getAll()) {
            try {

                Set<String> guildMembers =
                        new HashSet<>(dataService.getPlayerNamesBySwgohGGGuildUrl(guildDto.getUrl()));
                List<PlayerDto> current = playerService.findByGuild(guildDto.getId());
                current.stream()
                       .filter(playerDto -> !guildMembers.contains(playerDto.getName()))
                       .map(PlayerDto::getId)
                       .forEach(
                               player -> {
                                   disciplineHistoryService.deleteByPlayerId(player);
                                   playerService.delete(player);
                               }
                       );

                Set<String> tmp = current.stream()
                                         .map(PlayerDto::getName)
                                         .collect(Collectors.toSet());

                guildMembers.removeIf(tmp::contains);
                guildMembers.forEach(
                        player -> playerService.save(
                                PlayerDto.builder()
                                         .name(player)
                                         .guildId(guildDto.getId())
                                         .build()
                        )
                );

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
