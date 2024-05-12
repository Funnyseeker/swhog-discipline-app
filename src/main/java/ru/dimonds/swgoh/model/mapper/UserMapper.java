package ru.dimonds.swgoh.model.mapper;

import org.mapstruct.Mapper;
import ru.dimonds.swgoh.dao.entity.UserEntity;
import ru.dimonds.swgoh.model.dto.UserDto;

@Mapper(componentModel = "spring")
public interface UserMapper extends GenericMapper<Long, UserEntity, UserDto> {
}
