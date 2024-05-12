package ru.dimonds.swgoh.service.impl;

import org.springframework.stereotype.Service;
import ru.dimonds.swgoh.dao.entity.PlayerDisciplineHistoryEntity;
import ru.dimonds.swgoh.model.dto.PlayerDisciplineHistoryDto;

@Service
public class PlayerDisciplineHistoryServiceImpl
        extends CrudServiceImpl<Long, PlayerDisciplineHistoryEntity, PlayerDisciplineHistoryDto>
        implements PlayerDisciplineHistoryService
{
}
