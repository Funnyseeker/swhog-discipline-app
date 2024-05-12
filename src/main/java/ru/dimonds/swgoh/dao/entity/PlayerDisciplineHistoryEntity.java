package ru.dimonds.swgoh.dao.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.*;
import org.springframework.data.jpa.domain.AbstractPersistable;

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
public class PlayerDisciplineHistoryEntity extends AbstractPersistable<Long> {
    private OffsetDateTime       date;
    @ManyToOne
    @JoinColumn(name = "player_id")
    private PlayerEntity         player;
    @ManyToOne
    @JoinColumn(name = "rule_id")
    private DisciplineRuleEntity disciplineRule;
    private Long                 disciplinePoints;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        PlayerDisciplineHistoryEntity that = (PlayerDisciplineHistoryEntity) o;
        return Objects.equals(date, that.date) &&
               Objects.equals(player, that.player) &&
               Objects.equals(disciplinePoints, that.disciplinePoints);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), date, player, disciplinePoints);
    }
}
