package ru.dimonds.swgoh.dao.entity;

import io.hypersistence.utils.hibernate.type.range.PostgreSQLRangeType;
import io.hypersistence.utils.hibernate.type.range.Range;
import jakarta.persistence.*;
import jakarta.persistence.Table;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.*;
import org.hibernate.annotations.Parameter;
import org.hibernate.id.enhanced.SequenceStyleGenerator;
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
@GenericGenerator(
        name = "sequence-generator",
        type = SequenceStyleGenerator.class,
        parameters = {
                @Parameter(name = "sequence_name", value = "discipline_rules_seq"),
                @Parameter(name = "initial_value", value = "1"),
                @Parameter(name = "increment_size", value = "1")
        }
)
@DynamicInsert
@DynamicUpdate
public class DisciplineRuleEntity extends AbstractEntity<Long> {
    @ManyToOne
    @JoinColumn(name = "guild_id")
    private GuildEntity guild;
    @Column(columnDefinition = "text")
    private String reason;
    private Long   disciplinePoints;
    @Column(columnDefinition = "text")
    private String      botType;
    @Type(PostgreSQLRangeType.class)
    @Column(name = "rule_values")
    private Range<Long> ruleValues;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        DisciplineRuleEntity that = (DisciplineRuleEntity) o;
        return Objects.equals(guild, that.guild) &&
               Objects.equals(reason, that.reason) &&
               Objects.equals(disciplinePoints, that.disciplinePoints);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), guild, reason, disciplinePoints);
    }
}
