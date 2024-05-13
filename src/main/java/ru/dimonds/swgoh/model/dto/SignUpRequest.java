package ru.dimonds.swgoh.model.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.jackson.Jacksonized;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Jacksonized
public class SignUpRequest {
    private String guildName;
    private String guildUrl;
    @Email(message = "username must be email")
    @NotBlank(message = "Username is required")
    private String username;
    private String pwd;
}
