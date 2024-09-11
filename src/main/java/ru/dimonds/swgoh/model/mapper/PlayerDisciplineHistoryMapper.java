package ru.dimonds.swgoh.model.mapper;

import org.mapstruct.Mapper;
import ru.dimonds.swgoh.dao.entity.PlayerDisciplineHistoryEntity;
import ru.dimonds.swgoh.model.dto.PlayerDisciplineHistoryDto;

@Mapper(componentModel = "spring", uses = {DisciplineRuleMapper.class})
public abstract class PlayerDisciplineHistoryMapper
        extends GenericMapper<Long, PlayerDisciplineHistoryEntity, PlayerDisciplineHistoryDto>
{
}
