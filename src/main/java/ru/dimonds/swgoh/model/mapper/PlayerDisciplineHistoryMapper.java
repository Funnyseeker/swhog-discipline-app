package ru.dimonds.swgoh.model.mapper;

import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import ru.dimonds.swgoh.dao.entity.PlayerDisciplineHistoryEntity;
import ru.dimonds.swgoh.dao.entity.PlayerEntity;
import ru.dimonds.swgoh.dao.repo.PlayerEntityRepository;
import ru.dimonds.swgoh.model.dto.PlayerDisciplineHistoryDto;

@Mapper(componentModel = "spring", uses = {DisciplineRuleMapper.class})
public abstract class PlayerDisciplineHistoryMapper
        extends GenericMapper<Long, PlayerDisciplineHistoryEntity, PlayerDisciplineHistoryDto>
{
    @Autowired
    private PlayerEntityRepository playerRepository;

    public Long map(PlayerEntity value) {
        return value.getId();
    }

    public PlayerEntity map(Long value) {
        return playerRepository.findById(value).orElse(null);
    }
}
