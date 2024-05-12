package ru.dimonds.swgoh.service;

import ru.dimonds.swgoh.dao.entity.GuildEntity;
import ru.dimonds.swgoh.model.dto.GuildDto;

import java.util.Optional;

public interface GuildService extends CrudService<Long, GuildEntity, GuildDto> {
    Optional<GuildDto> findGuildByUrl(String url);
}
