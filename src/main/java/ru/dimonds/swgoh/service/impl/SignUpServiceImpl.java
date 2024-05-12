package ru.dimonds.swgoh.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.dimonds.swgoh.exception.DisciplineAppException;
import ru.dimonds.swgoh.model.dto.PlayerDto;
import ru.dimonds.swgoh.model.dto.UserDto;
import ru.dimonds.swgoh.model.request.SignUpRequest;
import ru.dimonds.swgoh.service.PlayerService;
import ru.dimonds.swgoh.service.SignUpService;
import ru.dimonds.swgoh.service.SwgohDataService;
import ru.dimonds.swgoh.service.UserService;

import javax.xml.bind.DatatypeConverter;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.Clock;
import java.util.List;
import java.util.Random;

@Service
@Slf4j
public class SignUpServiceImpl implements SignUpService {

    @Autowired
    private SwgohDataService swgohDataService;
    @Autowired
    private UserService      userService;
    @Autowired
    private PlayerService    playerService;

    @Override
    public void signUp(SignUpRequest request) throws DisciplineAppException {
        try {
            List<String> players = swgohDataService.getPlayerNamesBySwgohGGGuildUrl(request.getGuildUrl());
            Random       random  = new Random(Clock.systemUTC().millis());
            byte[]       salt    = new byte[32];
            random.nextBytes(salt);
            userService.save(
                    UserDto.builder()
                           .username(request.getUsername())
                           .pwd(getMD5Hash(request.getPwd(), salt))
                           .salt(salt)
                           .build()
            );
            players.stream()
                   .map(
                           player -> PlayerDto.builder()
                                              .name(player)
                                              .build()
                   )
                   .toList()
                   .forEach(playerService::save);
        } catch (Exception e) {
            log.error("SignUp failed:", e);
            throw new DisciplineAppException(e);
        }
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
