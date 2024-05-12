package ru.dimonds.swgoh.service.impl;

import org.springframework.stereotype.Service;
import ru.dimonds.swgoh.dao.entity.UserEntity;
import ru.dimonds.swgoh.model.dto.UserDto;
import ru.dimonds.swgoh.service.UserService;

@Service
public class UserServiceImpl extends CrudServiceImpl<Long, UserEntity, UserDto> implements UserService {
}
