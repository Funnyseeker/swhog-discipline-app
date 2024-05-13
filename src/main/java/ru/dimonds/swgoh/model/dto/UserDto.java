package ru.dimonds.swgoh.model.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.dimonds.swgoh.enums.UserRoleEnum;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDto {
    private Long         id;
    @Email(message = "username must be email")
    @NotBlank(message = "Username is required")
    private String       username;
    private String       pwd;
    private byte[]       salt;
    private UserRoleEnum role;
    private String       discordNick;
    private String       swgohAllyCode;
    private String       comment;
    private Long         guildId;
}
