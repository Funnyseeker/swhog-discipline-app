package ru.dimonds.swgoh.dao.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.hibernate.id.enhanced.SequenceStyleGenerator;

import java.time.OffsetDateTime;
import java.util.Objects;

@Entity
@Table(name = "player_discipline_history")
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
                @Parameter(name = "sequence_name", value = "player_discipline_history_seq"),
                @Parameter(name = "initial_value", value = "1"),
                @Parameter(name = "increment_size", value = "1")
        }
)
public class PlayerDisciplineHistoryEntity extends AbstractEntity<Long> {
    private OffsetDateTime date;
    @ManyToOne
    @JoinColumn(name = "player_id")
    private PlayerEntity   player;
    @Column(columnDefinition = "text")
    private String         reason;
    private Long           disciplinePoints;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PlayerDisciplineHistoryEntity that = (PlayerDisciplineHistoryEntity) o;
        return Objects.equals(date, that.date) &&
               Objects.equals(player, that.player) &&
               Objects.equals(reason, that.reason) &&
               Objects.equals(disciplinePoints, that.disciplinePoints);
    }

    @Override
    public int hashCode() {
        return Objects.hash(date, player, reason, disciplinePoints);
    }
}
