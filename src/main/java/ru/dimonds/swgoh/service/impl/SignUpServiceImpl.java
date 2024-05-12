package ru.dimonds.swgoh.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.dimonds.swgoh.exception.DisciplineAppException;
import ru.dimonds.swgoh.model.dto.GuildDto;
import ru.dimonds.swgoh.model.dto.PlayerDto;
import ru.dimonds.swgoh.model.dto.UserDto;
import ru.dimonds.swgoh.model.request.SignUpRequest;
import ru.dimonds.swgoh.service.*;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@Slf4j
public class SignUpServiceImpl implements SignUpService {

    @Autowired
    private SwgohDataService swgohDataService;
    @Autowired
    private UserService      userService;
    @Autowired
    private PlayerService    playerService;
    @Autowired
    private GuildService     guildService;

    @Override
    public void signUp(SignUpRequest request) throws DisciplineAppException {
        try {
            Optional<GuildDto> guildDto = guildService.findGuildByUrl(request.getGuildUrl());
            if (guildDto.isPresent()) {
                throw new DisciplineAppException("Guild already registered.");
            } else {
                List<String> players = swgohDataService.getPlayerNamesBySwgohGGGuildUrl(request.getGuildUrl());
                GuildDto newGuild = guildService.save(
                        GuildDto.builder()
                                .url(request.getGuildUrl())
                                .name(request.getGuildName())
                                .lastSyncDate(OffsetDateTime.now())
                                .build()
                );
                UserDto userDto = userService.crateUser(request);
                newGuild.setUserList(Set.of(userDto.getId()));
                players.stream()
                       .map(
                               player -> PlayerDto.builder()
                                                  .name(player)
                                                  .build()
                       )
                       .toList()
                       .forEach(playerService::save);
            }
        } catch (Exception e) {
            log.error("SignUp failed:", e);
            throw new DisciplineAppException(e);
        }
    }
}
