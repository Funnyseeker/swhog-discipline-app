package ru.dimonds.swgoh.service;

import ru.dimonds.swgoh.dao.entity.UserEntity;
import ru.dimonds.swgoh.model.dto.UserDto;

public interface UserService extends CrudService<Long, UserEntity, UserDto>{
}
