package ru.dimonds.swgoh.model.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.dimonds.swgoh.dao.entity.PlayerDisciplineHistoryEntity;
import ru.dimonds.swgoh.dao.entity.PlayerEntity;
import ru.dimonds.swgoh.model.dto.PlayerDto;

import java.util.Set;

@Mapper(componentModel = "spring", uses = {PlayerDisciplineHistoryMapper.class})
public interface PlayerMapper extends GenericMapper<Long, PlayerEntity, PlayerDto> {

    @Override
    @Mapping(target = "disciplinePointsTotal", expression = "java(getTotalPoints(entity.getPlayerDisciplineHistory()))")
    PlayerDto toDto(PlayerEntity entity);

    default Long getTotalPoints(Set<PlayerDisciplineHistoryEntity> playerDisciplineHistoryEntities) {
        if (playerDisciplineHistoryEntities == null) {
            return 0L;
        }
        return playerDisciplineHistoryEntities.stream()
                                              .mapToLong(PlayerDisciplineHistoryEntity::getDisciplinePoints)
                                              .sum();
    }
}
