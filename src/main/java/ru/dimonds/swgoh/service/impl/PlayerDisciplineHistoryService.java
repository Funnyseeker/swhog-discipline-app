package ru.dimonds.swgoh.service.impl;

import ru.dimonds.swgoh.dao.entity.PlayerDisciplineHistoryEntity;
import ru.dimonds.swgoh.model.dto.PlayerDisciplineHistoryDto;
import ru.dimonds.swgoh.service.CrudService;

public interface PlayerDisciplineHistoryService
        extends CrudService<Long, PlayerDisciplineHistoryEntity, PlayerDisciplineHistoryDto>
{
}
