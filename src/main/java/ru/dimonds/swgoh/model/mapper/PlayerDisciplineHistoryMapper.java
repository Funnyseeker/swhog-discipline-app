package ru.dimonds.swgoh.model.mapper;

import org.mapstruct.Mapper;
import ru.dimonds.swgoh.dao.entity.PlayerDisciplineHistoryEntity;
import ru.dimonds.swgoh.dao.entity.PlayerEntity;
import ru.dimonds.swgoh.model.dto.PlayerDisciplineHistoryDto;

@Mapper(componentModel = "spring", uses = {DisciplineRuleMapper.class})
public interface PlayerDisciplineHistoryMapper
        extends GenericMapper<Long, PlayerDisciplineHistoryEntity, PlayerDisciplineHistoryDto>
{
    default Long map(PlayerEntity value) {
        return value.getId();
    }

    default PlayerEntity map(Long value) {
        return PlayerEntity.builder()
                           .id(value)
                           .build();
    }
}
