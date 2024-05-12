package ru.dimonds.swgoh.dao.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.hibernate.id.enhanced.SequenceStyleGenerator;

import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "players")
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
@SuperBuilder
@GenericGenerator(
        name = "sequence-generator",
        type = SequenceStyleGenerator.class,
        parameters = {
                @Parameter(name = "sequence_name", value = "players_seq"),
                @Parameter(name = "initial_value", value = "1"),
                @Parameter(name = "increment_size", value = "50"),
                @Parameter(name = "optimizer", value = "hilo")
        }
)
public class PlayerEntity extends AbstractEntity<Long> {
    @Column(columnDefinition = "text")
    private String                             name;
    private String                             discordNickName;
    private String                             swgohAllyCode;
    @OneToMany(mappedBy = "player")
    @ToString.Exclude
    private Set<PlayerDisciplineHistoryEntity> playerDisciplineHistory;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PlayerEntity that = (PlayerEntity) o;
        return Objects.equals(name, that.name) &&
               Objects.equals(
                       discordNickName,
                       that.discordNickName
               ) &&
               Objects.equals(swgohAllyCode, that.swgohAllyCode) &&
               Objects.equals(playerDisciplineHistory, that.playerDisciplineHistory);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, discordNickName, swgohAllyCode, playerDisciplineHistory);
    }
}