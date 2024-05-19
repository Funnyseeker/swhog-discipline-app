package ru.dimonds.swgoh.model.mapper;

import org.mapstruct.Mapper;
import ru.dimonds.swgoh.dao.entity.GuildEntity;
import ru.dimonds.swgoh.model.dto.GuildDto;

@Mapper(componentModel = "spring", uses = {UserMapper.class})
public abstract class GuildMapper extends GenericMapper<Long, GuildEntity, GuildDto> {
}
