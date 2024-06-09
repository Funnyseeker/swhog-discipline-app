package ru.dimonds.swgoh.service;

import org.springframework.data.domain.Sort;
import ru.dimonds.swgoh.dao.entity.PlayerEntity;
import ru.dimonds.swgoh.model.dto.PlayerDto;
import ru.dimonds.swgoh.model.dto.SearchFiltersDto;

import java.util.List;

public interface PlayerService extends CrudService<Long, PlayerEntity, PlayerDto> {
    List<PlayerDto> getAll(SearchFiltersDto filtersDto, Sort sort);

    List<PlayerDto> findByGuild(Long guildId);
}
