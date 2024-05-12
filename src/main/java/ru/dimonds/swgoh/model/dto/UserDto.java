package ru.dimonds.swgoh.model.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDto {
    private Long   id;
    private String username;
    private String pwd;
    private byte[] salt;
    private String discordNick;
    private String swgohAllyCode;
    private String comment;
    private Long   guildId;
}
