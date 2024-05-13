package ru.dimonds.swgoh.service;

import ru.dimonds.swgoh.dao.entity.UserEntity;
import ru.dimonds.swgoh.model.dto.UserDto;
import ru.dimonds.swgoh.model.dto.SignUpRequest;

import java.security.NoSuchAlgorithmException;

public interface UserService extends CrudService<Long, UserEntity, UserDto>{
    UserDto crateUser(SignUpRequest request) throws NoSuchAlgorithmException;
}
