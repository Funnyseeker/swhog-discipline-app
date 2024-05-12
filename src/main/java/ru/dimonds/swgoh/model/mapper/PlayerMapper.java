package ru.dimonds.swgoh.model.mapper;

import org.mapstruct.Mapper;
import ru.dimonds.swgoh.dao.entity.PlayerEntity;
import ru.dimonds.swgoh.model.dto.PlayerDto;

@Mapper(componentModel = "spring")
public interface PlayerMapper extends GenericMapper<Long, PlayerEntity, PlayerDto> {
}
