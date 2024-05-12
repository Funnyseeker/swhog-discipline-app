package ru.dimonds.swgoh.dao.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.data.jpa.domain.AbstractPersistable;

import java.util.Objects;

@Entity
@Table(name = "discipline_rules")
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
public class DisciplineRuleEntity extends AbstractPersistable<Long> {
    @Column(columnDefinition = "text")
    private String reason;
    private Long   disciplinePoints;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        DisciplineRuleEntity that = (DisciplineRuleEntity) o;
        return Objects.equals(reason, that.reason) &&
               Objects.equals(disciplinePoints, that.disciplinePoints);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), reason, disciplinePoints);
    }
}
