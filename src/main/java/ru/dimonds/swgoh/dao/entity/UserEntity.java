package ru.dimonds.swgoh.dao.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.hibernate.id.enhanced.SequenceStyleGenerator;
import org.springframework.data.jpa.domain.AbstractPersistable;

import java.util.Arrays;
import java.util.Objects;

@Entity
@Table(name = "users")
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
@GenericGenerator(
        name = "sequence-generator",
        type = SequenceStyleGenerator.class,
        parameters = {
                @Parameter(name = "sequence_name", value = "users_seq"),
                @Parameter(name = "initial_value", value = "1"),
                @Parameter(name = "increment_size", value = "1")
        }
)
public class UserEntity extends AbstractEntity<Long> {
    private String      username;
    private String      pwd;
    private byte[]      salt;
    private String      discordNick;
    private String      swgohAllyCode;
    private String      comment;
    @ManyToOne
    @JoinColumn(name = "guild_id")
    private GuildEntity guild;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        UserEntity that = (UserEntity) o;
        return Objects.equals(username, that.username) &&
               Objects.equals(pwd, that.pwd) &&
               Objects.deepEquals(salt, that.salt) &&
               Objects.equals(discordNick, that.discordNick) &&
               Objects.equals(swgohAllyCode, that.swgohAllyCode) &&
               Objects.equals(comment, that.comment) &&
               Objects.equals(guild, that.guild);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
                super.hashCode(),
                username,
                pwd,
                Arrays.hashCode(salt),
                discordNick,
                swgohAllyCode,
                comment,
                guild
        );
    }
}
