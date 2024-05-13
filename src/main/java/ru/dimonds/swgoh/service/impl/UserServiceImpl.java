package ru.dimonds.swgoh.service.impl;

import org.springframework.stereotype.Service;
import ru.dimonds.swgoh.dao.entity.UserEntity;
import ru.dimonds.swgoh.enums.UserRoleEnum;
import ru.dimonds.swgoh.model.dto.SignUpRequest;
import ru.dimonds.swgoh.model.dto.UserDto;
import ru.dimonds.swgoh.service.UserService;

import javax.xml.bind.DatatypeConverter;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.Clock;
import java.util.Random;

@Service
public class UserServiceImpl extends CrudServiceImpl<Long, UserEntity, UserDto> implements UserService {
    @Override
    public UserDto crateUser(SignUpRequest request) throws NoSuchAlgorithmException {
        Random random = new Random(Clock.systemUTC().millis());
        byte[] salt   = new byte[32];
        random.nextBytes(salt);
        return save(
                UserDto.builder()
                       .username(request.getUsername())
                       .pwd(getMD5Hash(request.getPwd(), salt))
                       .salt(salt)
                       .role(UserRoleEnum.ADMIN)
                       .build()
        );
    }

    private String getMD5Hash(String data, byte[] salt) throws NoSuchAlgorithmException {
        MessageDigest md       = MessageDigest.getInstance("MD5");
        byte[]        bytes    = data.getBytes(StandardCharsets.UTF_8);
        byte[]        combined = new byte[bytes.length + salt.length];
        ByteBuffer    buffer   = ByteBuffer.wrap(combined);
        buffer.put(bytes);
        buffer.put(salt);
        md.update(buffer);
        byte[] digest = md.digest();
        return DatatypeConverter.printHexBinary(digest).toUpperCase();
    }
}
