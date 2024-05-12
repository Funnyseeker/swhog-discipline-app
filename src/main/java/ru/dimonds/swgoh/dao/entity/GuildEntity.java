package ru.dimonds.swgoh.dao.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.hibernate.id.enhanced.SequenceStyleGenerator;
import org.springframework.data.jpa.domain.AbstractPersistable;

import java.time.OffsetDateTime;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "guilds")
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
                @Parameter(name = "sequence_name", value = "guilds_seq"),
                @Parameter(name = "initial_value", value = "1"),
                @Parameter(name = "increment_size", value = "1")
        }
)
public class GuildEntity extends AbstractEntity<Long> {
    @Column(columnDefinition = "text")
    private String          url;
    @Column(columnDefinition = "text")
    private String          name;
    private OffsetDateTime  lastSyncDate;
    @OneToMany(mappedBy = "guild")
    @ToString.Exclude
    private Set<UserEntity> userList;
    @OneToMany(mappedBy = "guild")
    @ToString.Exclude
    private Set<DisciplineRuleEntity> disciplineRules;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GuildEntity that = (GuildEntity) o;
        return Objects.equals(url, that.url) &&
               Objects.equals(name, that.name) &&
               Objects.equals(lastSyncDate, that.lastSyncDate) &&
               Objects.equals(userList, that.userList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(url, name, lastSyncDate, userList);
    }
}