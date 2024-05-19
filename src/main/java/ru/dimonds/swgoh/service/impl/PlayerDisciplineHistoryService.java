package ru.dimonds.swgoh.service.impl;

import org.springframework.data.domain.Sort;
import ru.dimonds.swgoh.dao.entity.PlayerDisciplineHistoryEntity;
import ru.dimonds.swgoh.model.dto.PlayerDisciplineHistoryDto;
import ru.dimonds.swgoh.service.CrudService;

import java.util.List;

public interface PlayerDisciplineHistoryService
        extends CrudService<Long, PlayerDisciplineHistoryEntity, PlayerDisciplineHistoryDto>
{
    List<PlayerDisciplineHistoryDto> getByPlayerId(Long playerId, Sort sort);
}
