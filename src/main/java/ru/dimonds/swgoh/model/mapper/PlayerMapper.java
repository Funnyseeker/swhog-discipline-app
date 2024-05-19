package ru.dimonds.swgoh.model.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.dimonds.swgoh.dao.entity.PlayerDisciplineHistoryEntity;
import ru.dimonds.swgoh.dao.entity.PlayerEntity;
import ru.dimonds.swgoh.model.dto.PlayerDto;

import java.util.List;
import java.util.Set;

@Mapper(componentModel = "spring", uses = {PlayerDisciplineHistoryMapper.class})
public abstract class PlayerMapper extends GenericMapper<Long, PlayerEntity, PlayerDto> {

    @Override
    @Mapping(target = "disciplinePointsTotal", expression = "java(getTotalPoints(entity.getPlayerDisciplineHistory()))")
    public abstract PlayerDto toDto(PlayerEntity entity);

    protected Long getTotalPoints(List<PlayerDisciplineHistoryEntity> playerDisciplineHistoryEntities) {
        if (playerDisciplineHistoryEntities == null) {
            return 0L;
        }
        return playerDisciplineHistoryEntities.stream()
                                              .mapToLong(PlayerDisciplineHistoryEntity::getDisciplinePoints)
                                              .sum();
    }
}
